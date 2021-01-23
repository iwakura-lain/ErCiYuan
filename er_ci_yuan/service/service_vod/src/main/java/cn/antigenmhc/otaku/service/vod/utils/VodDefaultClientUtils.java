package cn.antigenmhc.otaku.service.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/22 21:33
 * @Version: 1.0
 **/
public class VodDefaultClientUtils {
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret){
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        //创建服务端
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
