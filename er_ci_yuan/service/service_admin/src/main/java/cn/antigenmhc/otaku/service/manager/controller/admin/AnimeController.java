package cn.antigenmhc.otaku.service.manager.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimePublishVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeQueryVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeVo;
import cn.antigenmhc.otaku.service.manager.service.AnimeDescriptionService;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("动漫列表分页")
    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(value = "当前页码", required = true) @PathVariable("page") Long page,
                           @ApiParam(value = "每页记录数", required = true) @PathVariable("limit") Long limit,
                           @ApiParam("查询条件") AnimeQueryVo queryVo){
        Page<AnimeVo> adminPage = new Page<>(page, limit);
        IPage<AnimeVo> pageModel = animeService.selectPageByQuery(adminPage, queryVo);
        List<AnimeVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return Result.ok().setData("total", total).setData("rows", records);
    }

    @ApiOperation("删除动漫")
    @DeleteMapping("delete/{id}")
    public Result deleteAnime(@PathVariable("id") String id){
        //批量删除该动漫下的所有视频，季度信息，视频信息
        animeService.deleteAllChapterAndAllVideoByAnimeId(id);
        //删除oss中的封面
        animeService.deleteCoverByAdminId(id);
        boolean removed = animeService.deleteAnimeAllInfoById(id);
        return removed ? Result.ok().setMessage("删除成功") : Result.error().setMessage("删除失败");
    }

    @ApiOperation("用于展示发布页面的信息")
    @GetMapping("get-publish-anime/{id}")
    public Result getAnimePublishInfo(@PathVariable("id") String id){
        AnimePublishVo publishInfo = animeService.getAnimePublishInfoById(id);

        if(publishInfo != null){
            return Result.ok().setData("item", publishInfo);
        }else{
            return Result.error().setMessage("不存在该数据");
        }
    }

    @ApiOperation("根据id更新对应 anime 的发布状态")
    @PutMapping("publish-anime/{id}")
    public Result updateAnimePublishStatus(@PathVariable("id") String id){
        boolean publishAnime = animeService.publishAnimeInfoById(id);

        if(publishAnime){
            return Result.ok().setMessage("发布成功");
        }else{
            return Result.error().setMessage("不存在该数据");
        }
    }

    @ApiOperation("根据关键字匹配，返回输入建议")
    @GetMapping("list/name/{key}")
    public Result getRecordsNameByKey(@PathVariable("key") String key){
        List<Map<String, String>> records = animeService.getRecordsNameByKey(key);
        return Result.ok().setMessage("查询成功").setData("records", records);
    }

}

