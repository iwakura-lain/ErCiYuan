package cn.antigenmhc.otaku.service.cms.remote.fallback;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.remote.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author helen
 * @since 2020/4/17
 */

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public Result deleteFile(String url) {
        log.info("熔断保护");
        return Result.error().setMessage("调用超时");
    }
}
