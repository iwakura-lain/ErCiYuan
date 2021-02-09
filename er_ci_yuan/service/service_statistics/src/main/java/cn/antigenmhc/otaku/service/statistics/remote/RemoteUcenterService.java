package cn.antigenmhc.otaku.service.statistics.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.statistics.remote.fallback.UcenterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/7 21:25
 * @Version: 1.0
 **/
@FeignClient(value = "service-ucenter", fallback = UcenterServiceFallback.class)
public interface RemoteUcenterService {

    @GetMapping("/admin/ucenter/member/register-count/{date}")
    Result getRegisterCount(@PathVariable("date") String date);
}
