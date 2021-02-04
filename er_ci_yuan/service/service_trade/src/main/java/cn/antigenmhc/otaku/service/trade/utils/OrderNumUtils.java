package cn.antigenmhc.otaku.service.trade.utils;

import io.netty.handler.codec.DateFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * 订单号工具类
 *
 * @author qy
 * @since 1.0
 */
public class OrderNumUtils {

    /**
     * 获取订单号
     * @return ：订单号
     */
    public static String getOrderNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String newDate = localDateTime.format(formatter);
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        return newDate + sb.toString();
    }
}
