package cn.antigenmhc.otaku.service.trade.remote;

import cn.antigenmhc.otaku.service.base.dto.MemberDto;
import cn.antigenmhc.otaku.service.trade.remote.fallback.RemoteUcenterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 23:10
 * @Version: 1.0
 **/
@FeignClient(value = "service-ucenter", fallback = RemoteUcenterServiceFallback.class)
public interface RemoteUcenterService {
    @GetMapping("api/site/member/inner/get-member-dto/{memberId}")
    MemberDto getMemberDtoByMemberId(@PathVariable("memberId") String memberId);
}
