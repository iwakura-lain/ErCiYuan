package cn.antigenmhc.otaku.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:34
 * @Version: 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
