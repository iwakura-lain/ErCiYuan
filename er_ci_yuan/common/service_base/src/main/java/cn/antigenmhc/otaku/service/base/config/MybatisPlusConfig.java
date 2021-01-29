package cn.antigenmhc.otaku.service.base.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: antigenMHC
 * @Date: 2020/11/30 20:52
 * @Version: 1.0
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("cn.antigenmhc.otaku.service.*.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean(name = "paginationInterceptor")
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(name = "optimisticLockerInnerInterceptor")
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
