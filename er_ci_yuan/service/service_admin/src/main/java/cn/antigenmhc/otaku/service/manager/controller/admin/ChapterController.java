package cn.antigenmhc.otaku.service.manager.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.Chapter;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.pojo.vo.ChapterVo;
import cn.antigenmhc.otaku.service.manager.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Api(description = "季度目录管理")
@RestController
@RequestMapping("/admin/manager/chapter")
@Slf4j
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @ApiOperation("新增季度")
    @PostMapping("add")
    public Result addVideo(
            @ApiParam("章节对象")
            @RequestBody Chapter chapter){
        boolean saved = chapterService.save(chapter);
        return saved ? Result.ok().setMessage("新增成功") :
                       Result.error().setMessage("新增失败");
    }

    @ApiOperation("根据 id 获得 chapter 信息")
    @GetMapping("get-by-id/{id}")
    public Result getChapterById(@PathVariable("id") String id){
        Chapter res = chapterService.getById(id);

        return res != null ?  Result.ok().setData("item", res).setMessage("查询成功"):
                              Result.error().setMessage("数据不存在");
    }

    @ApiOperation("更新季度")
    @PutMapping("update")
    public Result updateById(@RequestBody Chapter chapter){
        boolean updated = chapterService.updateById(chapter);
        return updated ? Result.ok().setMessage("更新成功") :
                Result.error().setMessage("更新失败");
    }

    @ApiOperation("删除季度")
    @DeleteMapping("delete/{id}")
    public Result deleteOneById(@PathVariable("id") String id){
        //批量删除属于该季下的所有视频以及视频信息
        chapterService.deleteAllVodByChapterId(id);
        boolean hashRemove = chapterService.removeChapterAndVideoById(id);
        return hashRemove ? Result.ok().setMessage("删除成功") : Result.error().setMessage("删除失败");
    }

    @ApiOperation("根据动漫 id 获得对应的嵌套目录")
    @GetMapping("nested-list/{id}")
    public Result getNestedListByAnimeId(
            @PathVariable("id") String animeId){
        List<ChapterVo> treeList = chapterService.getNestedList(animeId);
        return Result.ok().setData("items", treeList);
    }

}


