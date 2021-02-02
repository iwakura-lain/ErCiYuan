package cn.antigenmhc.otaku.service.ucenter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/1 13:12
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "gitee.oauth2")
public class GiteeOauth2Properties {
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String authorizeUrl;
    private String accessTokenUrl;
    private String userInfoUrl;
}
