package cn.antigenmhc.otaku.service.vod.controller.api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.vod.service.VodService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/26 21:46
 * @Version: 1.0
 **/
@Api("视频点播")
@RestController
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiMediaController {

    @Resource
    private VodService vodService;

    @GetMapping("get-play-url/{vodId}")
    public Result getPlayUrl(@PathVariable("vodId") String vodId){
        try {
            List<String> urls = vodService.getPlayUrl(vodId);
            return Result.ok().setData("urls", urls);
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.FETCH_PLAYURL_ERROR);
        }
    }

}
