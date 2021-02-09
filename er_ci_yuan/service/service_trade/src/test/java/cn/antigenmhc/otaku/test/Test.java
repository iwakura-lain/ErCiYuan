package cn.antigenmhc.otaku.test;

import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.trade.TradeApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/6 23:17
 * @Version: 1.0
 **/
@SpringBootTest(classes = {TradeApplication.class})
@RunWith(SpringRunner.class)

public class Test {

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @org.junit.Test
    public void test1(){
        redisUtil.set("123", "222", 18000);
        System.out.println(redisUtil.get("123"));
        String key = "1356490735013920770";
        String s = (String)redisTemplate.opsForValue().get(key);
        System.out.println(s);
        String o = (String)redisUtil.get(key);
        System.out.println(o);
    }
}
