package cn.antigenmhc.otaku.service.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author: antigenMHC
 * @Date: 2020/12/2 23:03
 * @Version: 1.0
 **/
@Configuration
@EnableSwagger2
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
                .apiInfo(apiInfo("后台管理", "admin管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.manager.controller.admin"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket aclApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("aclAPi")
                .apiInfo(apiInfo("权限管理", "权限管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.acl.controller.admin"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket siteApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("siteApi")
                .apiInfo(apiInfo("前台网站接口", "前台网站的api"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.manager.controller.site_api"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket fileApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fileApi")
                .apiInfo(apiInfo("文件管理", "文件管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.oss.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket tradeApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("tradeApi")
                .apiInfo(apiInfo("订单", "订单接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.trade.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket memberApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("memberApi")
                .apiInfo(apiInfo("用户管理", "用户管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.ucenter.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket smsApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("smsAPi")
                .apiInfo(apiInfo("sms短信", "sms接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.sms.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket cmsApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cmsApi")
                .apiInfo(apiInfo("内容管理", "内容管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.cms.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket vodApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("vodApi")
                .apiInfo(apiInfo("vod管理", "vod管理接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.antigenmhc.otaku.service.vod.controller"))
                //请求路径过滤
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String title, String desc){
        //作者信息
        Contact contact = new Contact("antigenMHC",
                "https://www.yuque.com/antigenmhc",
                "723493929@qq.com");
        //文档介绍
        return new ApiInfo(title,
                desc,
                "2.0",
                "https://www.yuque.com/antigenmhc",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
