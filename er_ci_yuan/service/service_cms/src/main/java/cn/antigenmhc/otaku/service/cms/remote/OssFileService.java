package cn.antigenmhc.otaku.service.cms.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.remote.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/27 18:10
 * @Version: 1.0
 **/
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/delete")
    Result deleteFile(@RequestParam String url);
}
