package cn.antigenmhc.otaku.infrastructure.apigateway.filter;

import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.common.base.utils.JwtUtil;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import com.google.gson.JsonObject;
import io.jsonwebtoken.*;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/6 20:42
 * @Version: 1.0
 **/
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取请求，并获取请求的路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        //当请求路径中含有 /api/**/auth/** 时，进行鉴权
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(antPathMatcher.match("/api/**/auth/**", path)){

            //获取 token
            List<String> token = request.getHeaders().get("token");
            //没有携带 token，鉴权失败，返回错误信息
            if(token == null){
                return out(exchange.getResponse());
            }

            //token 不合法, 返回错误信息
            if(JwtUtil.checkTokenExpireTimeAndGetNew(token.get(0), redisUtil) == null){
                return out(exchange.getResponse());
            }

        }

        //过滤器链，传递请求
        return chain.filter(exchange);
    }

    /**
     * 定义当前过滤器的优先级，值越小，优先级越高
     * 值越小的过滤器越先被访问
     * @return ：优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }


    private Mono<Void> out(ServerHttpResponse response) {

        JsonObject message = new JsonObject();
        //统一响应结果
        message.addProperty("success", false);
        message.addProperty("code", 28040);
        message.addProperty("data", "");
        message.addProperty("message", "鉴权失败");
        //转换响应结果，并设置为 UTF-8 编码
        byte[] bytes = message.toString().getBytes(StandardCharsets.UTF_8);
        //包装响应结果到缓冲区
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //返回响应结果，并添加缓冲区数据
        return response.writeWith(Mono.just(buffer));
    }
}
