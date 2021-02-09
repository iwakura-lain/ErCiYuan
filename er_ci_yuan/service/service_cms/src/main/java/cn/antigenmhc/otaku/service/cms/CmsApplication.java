package cn.antigenmhc.otaku.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/07 14:58
 * @Version: 1.0
 **/
@SpringBootApplication
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
@EnableFeignClients
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}
