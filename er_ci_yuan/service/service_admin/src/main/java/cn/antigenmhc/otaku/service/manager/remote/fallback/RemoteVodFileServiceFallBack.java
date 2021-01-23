package cn.antigenmhc.otaku.service.manager.remote.fallback;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.remote.RemoteVodFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/22 22:38
 * @Version: 1.0
 **/
@Service
@Slf4j
public class RemoteVodFileServiceFallBack implements RemoteVodFileService {
    @Override
    public Result deleteVodFile(String id) {
        log.error("vod 文件删除失败，请检查 vod 服务");
        return Result.error().setMessage("vod 的文件删除接口挂掉了");
    }

}
