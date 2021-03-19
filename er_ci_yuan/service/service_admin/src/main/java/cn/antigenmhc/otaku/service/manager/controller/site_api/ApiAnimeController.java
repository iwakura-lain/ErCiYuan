package cn.antigenmhc.otaku.service.manager.controller.site_api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.base.dto.AnimeDto;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.vo.ChapterVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.SiteAnimeInfoVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.SiteAnimeQueryVo;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import cn.antigenmhc.otaku.service.manager.service.ChapterService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/25 17:09
 * @Version: 1.0
 **/
@Api(description = "前台动漫展示")
@RestController
@RequestMapping("/api/admin/anime")
public class ApiAnimeController {

    @Resource
    private AnimeService animeService;
    @Resource
    private ChapterService chapterService;

    @ApiOperation("根据查询对象获取动漫列表(暂时无用，用的下面那个分页的方法)")
    @GetMapping("get-anime-by-query")
    public Result getAnimeByQueryVo(SiteAnimeQueryVo siteAnimeQueryVo){
        List<Anime> animeList = animeService.siteSelectAnimeListByQuery(siteAnimeQueryVo);
        return Result.ok().setData("items", animeList).setMessage("查询成功");
    }

    @ApiOperation("前台动漫列表分页")
    @GetMapping("list")
    public Result listPage(@ApiParam("查询条件") SiteAnimeQueryVo queryVo){
        System.out.println(queryVo.getTitle());
        Page<Anime> animePage = new Page<>(queryVo.getPage(), queryVo.getSize());
        IPage<Anime> pageModel = animeService.siteSelectPageByQuery(animePage, queryVo);
        List<Anime> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return Result.ok().setData("total", total).setData("items", records);
    }

    @ApiOperation("动漫详情页")
    @GetMapping("anime-info/{id}")
    public Result getAnimeInfoById(@PathVariable("id") String animeId){
        SiteAnimeInfoVo animeInfoVo = animeService.siteSelectAnimeInfoAndUpdateViewCount(animeId);
        List<ChapterVo> nestedList = chapterService.getNestedList(animeId);
        return Result.ok().setData("item", animeInfoVo).setData("chapterList", nestedList).setMessage("获取成功");
    }

    @ApiOperation("搜索提示")
    @GetMapping("auto-list/{title}")
    public Result getAnimeByTitle2(@PathVariable String title){
        List<Map<String,String>> reList = animeService.getAnimeByTitle(title);
        return Result.ok().setData("animes", reList);
    }

    @ApiOperation("根据课程id，取出 AnimeDto")
    @GetMapping("inner/get-anime-dto/{animeId}")
    public AnimeDto getAnimeDtoByAnimeId(@PathVariable("animeId") String animeId){
        AnimeDto animeDto = animeService.getAnimeDtoByAnimeId(animeId);
        return animeDto;
    }

    @ApiOperation("更新销量")
    @GetMapping("inner/update-but-count/{animeId}")
    public Result updateAnimeBuyCount(@PathVariable("animeId") String animeId){
        animeService.updateAnimeBuyCount(animeId);
        return Result.ok();
    }

}
