package cn.antigenmhc.otaku.service.oss.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/5 21:18
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    private String endPoint;
    private String accesskeyId;
    private String accesskeySecret;
    private String bucketName;
}
