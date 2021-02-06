package cn.antigenmhc.otaku.service.trade.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/4 18:59
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
public class WxPayUrl implements Serializable {
    private static final long serialVersionUID=1L;

    public static final String TRADE_TYPE_NATIVE = "NATIVE";
    public static final String TRADE_TYPE_JS = "JS";
    public static final String TRADE_TYPE_APP = "APP";

    private String appId;
    private String mchId;
    private String nonceStr;
    private String body;
    private String outTradeNo;
    private String totalFee;
    private String spbillCreateIp;
    private String notifyUrl;
    private String tradeType;
}
