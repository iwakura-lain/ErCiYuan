package cn.antigenmhc.otaku.service.manager.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.remote.fallback.RemoteVodFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/22 22:36
 * @Version: 1.0
 **/
@FeignClient(value = "service-vod", fallback = RemoteVodFileServiceFallBack.class)
public interface RemoteVodFileService {

    @DeleteMapping("/admin/vod/file/delete/{id}")
    Result deleteVodFile(@PathVariable("id") String vodId);
}

