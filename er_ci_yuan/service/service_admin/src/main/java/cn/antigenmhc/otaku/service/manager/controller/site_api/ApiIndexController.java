package cn.antigenmhc.otaku.service.manager.controller.site_api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.service.AdminService;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/28 11:55
 * @Version: 1.0
 **/
@Api(description = "前台首页")
@RestController
@RequestMapping("/api/admin/index")
public class ApiIndexController {

    @Resource
    private AnimeService animeService;
    @Resource
    private AdminService adminService;

    @GetMapping("get-top-list")
    public Result getAnimeList(){
        List<Anime> animeList = animeService.getAnimeListToIndex();
        List<Admin> adminList = adminService.getAdminListToIndex();
        return Result.ok().setData("animes", animeList).setData("admins", adminList);
    }
}
