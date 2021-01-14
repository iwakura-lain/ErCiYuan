package cn.antigenmhc.otaku.service.manager.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.AnimeDescription;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.service.AnimeDescriptionService;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 动漫 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Api("动漫管理")
@RestController
@RequestMapping("/admin/manager/anime")
@Slf4j
public class AnimeController {

    private static final int SAVE_FORM = 0;
    private static final int UPDATE_FORM = 1;

    @Resource
    private AnimeService animeService;

    @Resource
    private AnimeDescriptionService descriptionService;

    @GetMapping("allanime")
    public List<Anime> getAll(){
        return animeService.list();
    }

    @ApiOperation("新增动漫基本信息")
    @PostMapping("add-anime-info")
    public Result saveAnimeInfo(
            @ApiParam("动漫基本信息") @RequestBody AnimeInfoForm animeInfoForm
            ){
        String animeId = animeService.saveOrUpdateAnimeInfo(animeInfoForm, SAVE_FORM);
        return Result.ok().setData("animeId", animeId).setMessage("新增动漫基本信息成功");
    }

    @ApiOperation("根据 id 获得 Anime 信息")
    @GetMapping("get-by-id/{id}")
    public Result getAnimeById(@PathVariable("id") String id){
        AnimeInfoForm resForm = animeService.getAnimeFormById(id);

        if(resForm != null){
            return Result.ok().setData("item", resForm).setMessage("查询成功");
        }else{
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("更新 Anime 基本信息")
    @PutMapping("update-anime-info")
    public Result updateAnime(
            @ApiParam("动漫基本信息") @RequestBody AnimeInfoForm animeInfoForm){
        String animeId = animeService.saveOrUpdateAnimeInfo(animeInfoForm, UPDATE_FORM);
        //更新时，animeId 可以返回也可以不返回，因为这时候前端已经保存了 animeId 了
        return Result.ok().setData("animeId", animeId).setMessage("更新动漫基本信息成功");
    }
}

