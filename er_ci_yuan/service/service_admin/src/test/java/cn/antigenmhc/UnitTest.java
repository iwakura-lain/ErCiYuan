package cn.antigenmhc;


import cn.antigenmhc.otaku.service.manager.AdminApplication;
import cn.antigenmhc.otaku.service.manager.mapper.AdminMapper;
import cn.antigenmhc.otaku.service.manager.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: antigenMHC
 * @Date: 2020/11/28 22:49
 * @Version: 1.0
 **/
@SpringBootTest(classes = {AdminApplication.class})
class UnitTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void dataBaseTest(){

    }
}
