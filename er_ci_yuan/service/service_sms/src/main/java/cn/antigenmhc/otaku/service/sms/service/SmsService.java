package cn.antigenmhc.otaku.service.sms.service;

import cn.antigenmhc.otaku.common.base.result.Result;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:49
 * @Version: 1.0
 **/
public interface SmsService {

    Result sendCheckCode(String mobile);
}
