package cn.antigenmhc.otaku.service.trade.remote;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.base.dto.AnimeDto;
import cn.antigenmhc.otaku.service.trade.remote.fallback.RemoteAdminServiceFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 23:03
 * @Version: 1.0
 **/
@FeignClient(value = "service-admin", fallback = RemoteAdminServiceFallback.class)
public interface RemoteAdminService {
    @GetMapping("/api/admin/anime/inner/get-anime-dto/{animeId}")
    AnimeDto getAnimeDtoByAnimeId(@PathVariable("animeId") String animeId);

    @GetMapping("/api/admin/anime/inner/update-but-count/{animeId}")
    Result updateAnimeBuyCount(@PathVariable("animeId") String animeId);
}
