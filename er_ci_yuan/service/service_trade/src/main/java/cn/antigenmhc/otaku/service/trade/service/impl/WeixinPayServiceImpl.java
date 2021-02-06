package cn.antigenmhc.otaku.service.trade.service.impl;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.trade.pojo.Order;
import cn.antigenmhc.otaku.service.trade.pojo.WxPayUrl;
import cn.antigenmhc.otaku.service.trade.properties.WxPayProperties;
import cn.antigenmhc.otaku.service.trade.service.OrderService;
import cn.antigenmhc.otaku.service.trade.service.WeixinPayService;
import cn.antigenmhc.otaku.service.trade.utils.HttpClientUtils;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/4 18:30
 * @Version: 1.0
 **/
@Service
@Slf4j
public class WeixinPayServiceImpl implements WeixinPayService {

    @Resource
    private OrderService orderService;
    @Resource
    private WxPayProperties wxPayProperties;

    @Override
    public Map<String, Object> createNativeQrCode(String orderNo, String remoteAddr) {

        try {
            //根据订单号获取订单
            Order order = orderService.getOrderByOrderNo(orderNo);

            //调用微信的统一下单api
            HttpClientUtils client = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/unifiedorder");

            WxPayUrl wxPayUrl =
                    new WxPayUrl()
                            .setAppId(wxPayProperties.getAppId())
                            .setMchId(wxPayProperties.getPartner())
                            .setNonceStr(WXPayUtil.generateNonceStr())
                            .setBody(order.getAnimeTitle())
                            .setOutTradeNo(orderNo)
                            .setTotalFee(order.getTotalFee().intValue() + "")
                            .setSpbillCreateIp(remoteAddr)
                            .setNotifyUrl(wxPayProperties.getNotifyUrl())
                            .setTradeType(WxPayUrl.TRADE_TYPE_NATIVE);

            //组装参数
            Map<String, String> params = setParams(wxPayUrl);

            //将参数转换成xml字符串，并且在字符串的最后追加计算的签名
            String xmlParams = WXPayUtil.generateSignedXml(params, wxPayProperties.getPartnerKey());

            //将参数放入请求对象的方法体
            client.setXmlParam(xmlParams);
            //使用https协议传输
            client.setHttps(true);
            //使用post方式发送请求
            client.post();
            //得到响应
            String resultXml = client.getContent();

            //将响应结果转为 Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);

            //检查 map，错误处理
            if (!checkCode(resultMap)) {
                throw new IntegrateException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
            }

            //要组装的结果对象
            Map<String, Object> map = new HashMap<>();
            //交易标识
            map.put("resultCode", resultMap.get("result_code"));
            //二维码url
            map.put("codeUrl", resultMap.get("code_url"));
            //动漫id
            map.put("animeId", order.getAnimeId());
            //金额
            map.put("totalFee", order.getTotalFee());
            //订单号
            map.put("outTradeNo", orderNo);

            return map;
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
        }
    }

    @Override
    public boolean checkSignature(String notifyXml) {
        try {
            return WXPayUtil.isSignatureValid(notifyXml, wxPayProperties.getPartnerKey());
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.PAY_ORDERQUERY_ERROR);
        }
    }



    private Map<String, String> setParams(WxPayUrl wxPayUrl){
        Map<String, String> params = new ConcurrentHashMap<String, String>();
        //appid
        params.put("appid", wxPayUrl.getAppId());
        //商户号
        params.put("mch_id", wxPayUrl.getMchId());
        //随机数
        params.put("nonce_str", wxPayUrl.getNonceStr());
        //商品描述
        params.put("body", wxPayUrl.getBody());
        //商户订单号
        params.put("out_trade_no", wxPayUrl.getOutTradeNo());
        //订单金额/分
        params.put("total_fee", wxPayUrl.getTotalFee());
        //终端 IP
        params.put("spbill_create_ip", wxPayUrl.getSpbillCreateIp());
        //回调地址
        params.put("notify_url", wxPayUrl.getNotifyUrl());
        //交易类型
        params.put("trade_type", wxPayUrl.getTradeType());
        return params;
    }

    private boolean checkCode(Map<String, String> resultMap){
        if("FAIL".equals(resultMap.get("return_code")) || "FAIL".equals(resultMap.get("result_code"))){
            log.error("微信支付统一下单错误 - "
                    + "return_code: " + resultMap.get("return_code")
                    + "return_msg: " + resultMap.get("return_msg")
                    + "result_code: " + resultMap.get("result_code")
                    + "err_code: " + resultMap.get("err_code")
                    + "err_code_des: " + resultMap.get("err_code_des"));
            return false;
        }
        return true;
    }
}
