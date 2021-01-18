package cn.antigenmhc.otaku.service.manager.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Chapter;
import cn.antigenmhc.otaku.service.manager.pojo.Video;
import cn.antigenmhc.otaku.service.manager.pojo.vo.ChapterVo;
import cn.antigenmhc.otaku.service.manager.service.ChapterService;
import cn.antigenmhc.otaku.service.manager.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 动漫视频 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Api("视频管理")
@RestController
@RequestMapping("/admin/manager/video")
@Slf4j
public class VideoController {
    @Resource
    private VideoService videoService;

    @ApiOperation("新增视频")
    @PostMapping("add")
    public Result addVideo(
            @ApiParam("视频对象")
            @RequestBody Video video){
        boolean saved = videoService.save(video);
        return saved ? Result.ok().setMessage("新增成功") :
                Result.error().setMessage("新增失败");
    }

    @ApiOperation("根据 id 获得视频信息")
    @GetMapping("get-by-id/{id}")
    public Result getChapterById(@PathVariable("id") String id){
        Video res = videoService.getById(id);

        return res != null ?  Result.ok().setData("item", res).setMessage("查询成功"):
                Result.error().setMessage("数据不存在");
    }

    @ApiOperation("更新视频信息")
    @PutMapping("update")
    public Result updateById(@RequestBody Video video){
        boolean updated = videoService.updateById(video);
        return updated ? Result.ok().setMessage("更新成功") :
                Result.error().setMessage("更新失败");
    }

    @ApiOperation("删除视频")
    @DeleteMapping("delete/{id}")
    public Result deleteOneById(@PathVariable("id") String id){
        //chapterService.deleteVodByChapterId(id);
        boolean hashRemove = videoService.removeById(id);
        return hashRemove ? Result.ok().setMessage("删除成功") : Result.error().setMessage("删除失败");
    }

}

