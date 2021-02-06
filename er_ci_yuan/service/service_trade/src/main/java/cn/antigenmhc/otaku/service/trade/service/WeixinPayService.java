package cn.antigenmhc.otaku.service.trade.service;

import cn.antigenmhc.otaku.service.trade.pojo.Order;

import java.util.Map;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/4 18:29
 * @Version: 1.0
 **/
public interface WeixinPayService {
    /**
     * 获取支付二维码地址
     * @param orderNo：唯一订单号
     * @param remoteAddr：终端 IP
     * @return ：vx 服务器返回的 xml 转成 Map
     */
    Map<String, Object> createNativeQrCode(String orderNo, String remoteAddr);

    /**
     * 验证签名
     * @param notifyXml：返回的 xml 字符串
     * @return ：回调是否被伪造
     */
    boolean checkSignature(String notifyXml);

}
