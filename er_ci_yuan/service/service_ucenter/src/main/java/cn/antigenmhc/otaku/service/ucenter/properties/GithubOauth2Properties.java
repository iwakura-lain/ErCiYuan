package cn.antigenmhc.otaku.service.ucenter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/31 18:46
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "github.oauth2")
public class GithubOauth2Properties {
    private String clientId;
    private String clientSecret;
    private String authorizeUrl;
    private String redirectUrl;
    private String accessTokenUrl;
    private String userInfoUrl;
}
