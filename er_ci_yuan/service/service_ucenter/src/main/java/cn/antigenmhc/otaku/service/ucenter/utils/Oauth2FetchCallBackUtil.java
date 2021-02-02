package cn.antigenmhc.otaku.service.ucenter.utils;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.base.utils.RedisUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 0:45
 * @Version: 1.0
 **/
public class Oauth2FetchCallBackUtil {

    public static boolean fetchCallBackState(String code, String state, RedisUtil redisUtil){
        if(StringUtils.isEmptyOrWhitespaceOnly(code) ||
                StringUtils.isEmptyOrWhitespaceOnly(state)){
            return false;
        }
        String redisState = (String)redisUtil.get((String) redisUtil.get(state));
        if(!redisState.equals(state)){
            return false;
        }
        return true;
    }


}
