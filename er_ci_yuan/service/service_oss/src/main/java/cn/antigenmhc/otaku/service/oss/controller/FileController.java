package cn.antigenmhc.otaku.service.oss.controller;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.oss.service.FileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/5 22:30
 * @Version: 1.0
 **/
@Api(tags = "阿里云文件管理")
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {
    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result upload(@RequestParam MultipartFile file,
                         @RequestParam String moduleName) {
        try {
            InputStream inputStream = file.getInputStream();
            String name = file.getOriginalFilename();

            String url = fileService.upload(inputStream, moduleName, name);
            return Result.ok().setMessage("文件上传成功").setData("url", url);
        } catch (Exception e) {
            //记录没被统一异常处理过的异常信息
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation("删除文件")
    @DeleteMapping("delete")
    public Result deleteFile(@RequestParam String url){
        fileService.deleteFile(url);
        return Result.ok().setMessage("删除文件成功");
    }

    @ApiOperation("批量删除文件")
    @DeleteMapping("delete-files")
    public Result deleteFiles(@RequestParam List<String> urls){
        fileService.deleteFiles(urls);
        return Result.ok().setMessage("批量删除文件成功");
    }
}
