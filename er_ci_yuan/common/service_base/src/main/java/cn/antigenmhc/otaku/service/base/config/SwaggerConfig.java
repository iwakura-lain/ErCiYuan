package cn.antigenmhc.otaku.service.base.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;

/**
 * @Author: antigenMHC
 * @Date: 2020/12/2 23:03
 * @Version: 1.0
 **/
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

//    @Bean
//    public Docket webApiConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(adminApiInfo())
//                .select()
//                //.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
//                .paths(PathSelectors.regex("/api/.*"))
//                .build();
//    }

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.manager.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo(){
        //作者信息
        Contact contact = new Contact("antigenMHC",
                "https://www.yuque.com/antigenmhc",
                "723493929@qq.com");
        //文档介绍
        return new ApiInfo("admin接口",
                "后台管理员接口",
                "2.0",
                "https://www.yuque.com/antigenmhc",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
