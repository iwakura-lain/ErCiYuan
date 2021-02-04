package cn.antigenmhc.otaku.service.trade.remote.fallback;

import cn.antigenmhc.otaku.service.base.dto.AnimeDto;
import cn.antigenmhc.otaku.service.trade.remote.RemoteAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 23:06
 * @Version: 1.0
 **/
@Service
@Slf4j
public class RemoteAdminServiceFallback implements RemoteAdminService {
    @Override
    public AnimeDto getAnimeDtoByAnimeId(String animeId) {
        return null;
    }
}
