package cn.antigenmhc.otaku.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 21:17
 * @Version: 1.0
 **/
@SpringBootApplication
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
@EnableFeignClients
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
