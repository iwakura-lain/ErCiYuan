package cn.antigenmhc.otaku.service.trade.remote.fallback;

import cn.antigenmhc.otaku.service.base.dto.MemberDto;
import cn.antigenmhc.otaku.service.trade.remote.RemoteUcenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/3 9:30
 * @Version: 1.0
 **/
@Service
@Slf4j
public class RemoteUcenterServiceFallback implements RemoteUcenterService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        return null;
    }
}
