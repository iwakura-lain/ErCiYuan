package cn.antigenmhc.otaku.service.base.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/1 11:35
 * @Version: 1.0
 **/
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig{

    @Bean
    @ConditionalOnMissingBean(name = "cookieSerializer")
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        //设置存到 cookie 中的 session 叫 JSESSIONID，
        //JSESSIONID 是前后端访问默认的 session_id 名称，可以通过 HttpSession.getId() 获取
        //原本默认存储的到 cookie 中的叫做 SESSION
        //设置为 JSESSIONID 就可以达到
        //前端传递session->后端getId->redis校验 的效果
        //即使不设置，也可以通过 HttpSession.getAttribute()的形式来指定获得 SESSION
        cookieSerializer.setCookieName("JSESSIONID");
        //path 不同的 cookie 无法互相读取
        //比如 xx.com/a/b 下的 cookie 默认 path 为 /a
        // xx.com/x/y 下的 cookie 默认 path 为 /x
        //因此 xx.com/x/y 路径中无法读取 path 为 /a 下的 cookie
        //设置为 / 则表示根目录下，该 cookie 可以被所有路径读取到
        //这种设置后，即使是将 session 存到 /a/b 下的 cookie 中，
        //其 path 也是 /，可以在 /x/y 下被读取
        cookieSerializer.setCookiePath("/");
        //设置写入的域名的正则，可以达到同一父域名下跨域访问cookie的效果，即将所有的cookie写到父域名下
        cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return cookieSerializer;
    }


}
