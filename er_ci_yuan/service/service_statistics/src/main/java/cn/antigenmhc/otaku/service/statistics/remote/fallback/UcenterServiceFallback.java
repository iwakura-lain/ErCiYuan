package cn.antigenmhc.otaku.service.statistics.remote.fallback;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.base.dto.RegisterDto;
import cn.antigenmhc.otaku.service.statistics.remote.RemoteUcenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/7 21:26
 * @Version: 1.0
 **/
@Service
@Slf4j
public class UcenterServiceFallback implements RemoteUcenterService {
    @Override
    public RegisterDto getRegisterCount(String date) {
        log.error("远程调用失败");
        return null;
    }
}
