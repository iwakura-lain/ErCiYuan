package cn.antigenmhc.otaku.service.sms.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.sms.service.SmsService;
import cn.antigenmhc.otaku.common.base.utils.FormUtils;
import cn.antigenmhc.otaku.service.sms.utils.RandomUtils;
import cn.antigenmhc.otaku.service.sms.utils.SmsProperties;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:49
 * @Version: 1.0
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SmsProperties smsProperties;

    @Override
    public Result sendCheckCode(String mobile) {
        //校验手机号是否合法
        if(StringUtils.isEmptyOrWhitespaceOnly(mobile)){
            return Result.error().setMessage("手机号不能为空").setCode(28019);
        }
        if(!FormUtils.isMobile(mobile)){
            return Result.error().setMessage("请输入正确的手机号").setCode(28029);
        }

        //生成验证码
        String sixCheckCode = RandomUtils.getSixBitRandom();

        //调用sdk，发送验证码到阿里云，发送短信，并判断 code 以返回信息
        String code = sendCode(sixCheckCode, mobile);
        if("isv.BUSINESS_LIMIT_CONTROL".equals(code)){
            return Result.setResult(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        //存储验证码到 redis, 并设置过期时间为 300s
        redisUtil.set(mobile, sixCheckCode, 300);

        return Result.ok().setMessage("发送成功，请查看手机");
    }

    /**
     * 短信发送
     * @param sixCheckCode：验证码
     * @param mobile：手机号
     * @return ：阿里云那边响应的 code
     */
    private String sendCode(String sixCheckCode, String mobile){
        String regionId = smsProperties.getRegionId();
        String accesskeyId= smsProperties.getAccessKeyId();
        String accesskeySecret = smsProperties.getAccessKeySecret();
        String templateCode = smsProperties.getTemplateCode();
        String signName = smsProperties.getSignName();

        DefaultProfile profile = DefaultProfile.getProfile(
                regionId, accesskeyId, accesskeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        //以下三个是固定值，根据从阿里云文档上cv来的代码而定
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        //设置参数
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("SignName", signName);

        Map<String, String> checkCode = new HashMap<>(8);
        checkCode.put("code", sixCheckCode);
        String code = new Gson().toJson(checkCode);
        request.putQueryParameter("TemplateParam", code);
        try{
            //发送短信
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            //解析响应的 json 数据
            Gson gson = new Gson();
            HashMap<String, String> dataMap = gson.fromJson(data, HashMap.class);
            //获取是否发送成功的信息，参考 sms 的 api
            String reCode = dataMap.get("Code");
            return reCode;
        }catch(Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.SMS_SEND_ERROR);
        }
    }
}
