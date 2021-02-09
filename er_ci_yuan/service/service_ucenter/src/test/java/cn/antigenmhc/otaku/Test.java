package cn.antigenmhc.otaku;

import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.UcenterApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/6 23:17
 * @Version: 1.0
 **/
@SpringBootTest(classes = {UcenterApplication.class})
@RunWith(SpringRunner.class)

public class Test {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @org.junit.Test
    public void test1(){
        String key = "1356490735013920770";
        redisUtil.set(key, "11111111111.eyJzdWIiOiJlcl9jaV95dWFuIiwiaWF0IjoxNjEyNjI2MjQzLCJleHAiOjE2MTI2NDQyNDMsImlkIjoiMTM1NjQ5MDczNTAxMzkyMDc3MCIsIm5pY2tuYW1lIjoibmV3IiwiYXZhdGFyIjoiaHR0cHM6Ly9hbnRpZ2VubWhjLWVyY2l5dWFuLm9zcy1jbi1oYW5nemhvdS5hbGl5dW5jcy5jb20vdWNlbnRlci8yMDIxLzAyLzA2LzA3Yjc3MjVmLWIxNmMtNGI5YS1hMjNkLTAyYjVkZWY2NmY3MS5qcGcifQ.gF2AekskwGC2DELj7xZ41syIAa77n9wHNT-J3DMqXrU", 18000);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        String s1 = stringRedisTemplate.opsForValue().get("1356474718523011074");
        System.out.println(s1);
        String s = (String)redisTemplate.opsForValue().get(key);
        System.out.println(s);
        String o = (String)redisUtil.get("1356474718523011074");
        System.out.println(o);
    }
}
