package cn.antigenmhc.otaku.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/21 19:00
 * @Version: 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"cn.antigenmhc.otaku"})
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
