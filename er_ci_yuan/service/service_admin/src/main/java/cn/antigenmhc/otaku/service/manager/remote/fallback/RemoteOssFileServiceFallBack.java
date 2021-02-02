package cn.antigenmhc.otaku.service.manager.remote.fallback;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.remote.RemoteOssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/9 23:11
 * @Version: 1.0
 **/
@Service
@Slf4j
public class RemoteOssFileServiceFallBack implements RemoteOssFileService {

    @Override
    public Result deleteFile(String url) {
        log.error("oss 文件删除失败，请检查 oss 服务");
        return Result.error().setMessage("oss 的文件删除接口挂掉了");
    }

    @Override
    public Result deleteFiles(List<String> urls) {
        log.error("oss 文件删除失败，请检查 oss 服务");
        return Result.error().setMessage("oss 的批量文件删除接口挂掉了");
    }

}
