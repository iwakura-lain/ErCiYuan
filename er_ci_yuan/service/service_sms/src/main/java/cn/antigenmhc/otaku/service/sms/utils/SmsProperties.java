package cn.antigenmhc.otaku.service.sms.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:36
 * @Version: 1.0
 **/
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties {

    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateCode;
    private String signName;
}
