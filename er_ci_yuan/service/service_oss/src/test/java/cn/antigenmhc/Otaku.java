package cn.antigenmhc;

import cn.antigenmhc.otaku.service.oss.OssApplication;
import cn.antigenmhc.otaku.service.oss.service.FileService;
import cn.antigenmhc.otaku.service.oss.service.serviceimpl.FileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/8 18:48
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OssApplication.class})
public class Otaku {

    @Autowired
    FileService fileService;

    @Test
    public void splitUrl(){
        String s = "https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/08/8ee2335b-e93c-459b-96bb-565e2a503db6.jpg";
        StringBuilder urlBuild = new StringBuilder(s);
        urlBuild.delete(0,urlBuild.indexOf("avatar"));
        System.out.println(urlBuild.toString());
    }

    @Test
    public void deleteFileTest(){
        String s = "https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/07/113ed0a7-95e1-4f33-ba41-044843c40167.jpg";
        fileService.deleteFile(s);
    }
}
