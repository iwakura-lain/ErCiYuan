package cn.antigenmhc.otaku.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/7 21:21
 * @Version: 1.0
 **/
@SpringBootApplication
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
