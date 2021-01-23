package cn.antigenmhc.otaku.service.vod.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/21 21:19
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {
    private String accesskeyId;
    private String accesskeySecret;
    private String templateGroupId;
    private String workflowId;
}