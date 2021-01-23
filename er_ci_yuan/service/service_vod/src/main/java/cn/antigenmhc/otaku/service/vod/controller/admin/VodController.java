package cn.antigenmhc.otaku.service.vod.controller.admin;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/22 16:38
 * @Version: 1.0
 **/
@Api(tags = "阿里云vod管理")
@RestController
@RequestMapping("/admin/vod/file")
@Slf4j
public class VodController {

    @Resource
    private VodService videoService;

    @ApiOperation("视频上传")
    @PostMapping("upload")
    public Result uploadVideo(@RequestParam("file")MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String videoId = videoService.uploadVideo(fileName, inputStream);
            return Result.ok().setData("videoId", videoId).setMessage("上传视频成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @ApiOperation("视频删除")
    @DeleteMapping("delete/{id}")
    public Result deleteVideo(@PathVariable("id") String vodId){
        try {
            videoService.deleteVideo(vodId);
            return Result.ok().setMessage("删除视频成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }
}
