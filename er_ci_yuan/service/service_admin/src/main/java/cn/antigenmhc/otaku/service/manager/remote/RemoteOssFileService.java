package cn.antigenmhc.otaku.service.manager.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/7 22:55
 * @Version: 1.0
 **/
@FeignClient("service-oss")
public interface RemoteOssFileService {

    @GetMapping("/admin/oss/file/test")
    Result test();
}
