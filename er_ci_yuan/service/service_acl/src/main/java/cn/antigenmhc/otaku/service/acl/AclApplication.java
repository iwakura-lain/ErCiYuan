package cn.antigenmhc.otaku.service.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/7 12:50
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.antigenmhc.otaku")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
