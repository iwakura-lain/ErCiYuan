package cn.antigenmhc.otaku.service.manager.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.remote.fallback.RemoteOssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/7 22:55
 * @Version: 1.0
 **/
@FeignClient(value = "service-oss", fallback = RemoteOssFileServiceFallBack.class)
public interface RemoteOssFileService {

    /**
     * oss 模块中删除文件的远程调用接口
     * @param url：文件 url
     * @return：结果
     */
    @DeleteMapping("/admin/oss/file/delete")
    Result deleteFile(@RequestParam String url);

    /**
     * oss 模块中批量删除文件的远程调用方法
     * @param urls：批量删除文件列表
     * @return：删除是否成功
     */
    @DeleteMapping("/admin/oss/file/delete-files")
    Result deleteFiles(@RequestParam List<String> urls);

}