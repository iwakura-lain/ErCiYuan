package cn.antigenmhc.otaku.service.manager.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.manager.pojo.Subject;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeSubjectVo;
import cn.antigenmhc.otaku.service.manager.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 动漫类别 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Api(description = "动漫类别管理")
@RestController
@RequestMapping("/admin/manager/subject")
@Slf4j
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @ApiOperation("Excel 批量导入动漫分类")
    @PostMapping("import")
    public Result batchImportByExcel(
            @RequestParam("file") MultipartFile file){

        InputStream excelInputStream = null;
        try {
            excelInputStream = file.getInputStream();
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new IntegrateException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }

        subjectService.batchImportByExcel(excelInputStream);
        return Result.ok().setMessage("批量导入成功");
    }

    @ApiOperation("嵌套数据列表，用于树形展示")
    @GetMapping("nested-list")
    public Result nestedAnimeSubjectList(){
        List<AnimeSubjectVo> treeList = subjectService.nestedAnimeSubjectList();
        return Result.ok().setData("items", treeList);
    }

    @ApiOperation("根据id删除类别")
    @DeleteMapping("delete/{id}")
    public Result deleteSubject(@PathVariable("id") String id){
        subjectService.removeById(id);
        return Result.ok().setMessage("删除成功");
    }

    @ApiOperation("添加类别")
    @PostMapping("add")
    public Result addSubject(@RequestBody Subject subject){
        subjectService.save(subject);
        return Result.ok().setMessage("添加成功");
    }

    @ApiOperation("更新类别")
    @PutMapping("update")
    public Result updateSubject(@RequestBody Subject subject){
        subjectService.updateById(subject);
        return Result.ok().setMessage("修改成功");
    }

}

