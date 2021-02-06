package cn.antigenmhc.otaku.service.trade.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/4 18:06
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "weixin.pay")
public class WxPayProperties {
    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;
}
