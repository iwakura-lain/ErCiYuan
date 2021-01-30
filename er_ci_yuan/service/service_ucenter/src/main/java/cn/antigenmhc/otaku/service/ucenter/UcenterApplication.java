package cn.antigenmhc.otaku.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 21:07
 * @Version: 1.0
 **/
@SpringBootApplication
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
