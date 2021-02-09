package cn.antigenmhc.otaku.service.manager.controller.site_api;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.common.base.utils.JwtUtil;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeCollectVo;
import cn.antigenmhc.otaku.service.manager.service.AnimeCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 动漫收藏 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Api(description = "动漫收藏")
@RestController
@RequestMapping("/api/admin/anime-collect")
public class AnimeCollectController {

    @Resource
    private AnimeCollectService animeCollectService;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("是否已经被收藏")
    @GetMapping("auth/is-collect/{animeId}")
    public Result isCollect(@PathVariable("animeId") String animeId,
                            HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo userInfo = JwtUtil.getMemberByJwtToken(newToken);

        boolean isCollect = animeCollectService.isCollect(animeId, userInfo.getId());
        return Result.ok().setData("isCollect", isCollect);
    }

    @ApiOperation("添加至收藏")
    @PostMapping("auth/save/{animeId}")
    public Result addCollectAnime(@PathVariable("animeId") String animeId,
                                  HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        boolean isSuccessAdd = animeCollectService.addCollectAnime(animeId, jwtInfo.getId());
        if(isSuccessAdd){
            return Result.ok().setMessage("收藏成功");
        }else{
            return Result.error().setMessage("收藏失败");
        }
    }

    @ApiOperation("取消收藏")
    @DeleteMapping("auth/remove/{animeId}")
    public Result deleteCollectAnime(@PathVariable("animeId") String animeId,
                                     HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        boolean isDeleted = animeCollectService.deleteCollectAnime(animeId, jwtInfo.getId());
        if(isDeleted){
            return Result.ok().setMessage("取消成功");
        }else{
            return Result.error().setMessage("取消失败");
        }
    }

    @ApiOperation("获取收藏列表")
    @GetMapping("auth/list")
    public Result getCollectList(HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        List<AnimeCollectVo> reList = animeCollectService.getAnimeCollectList(jwtInfo.getId());
        return Result.ok().setData("items", reList);
    }
}

