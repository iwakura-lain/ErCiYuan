package cn.antigenmhc.otaku.service.manager.controller.site_api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeSubjectVo;
import cn.antigenmhc.otaku.service.manager.service.SubjectService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/25 20:59
 * @Version: 1.0
 **/
@Api(description = "前台分类展示")
@RestController
@RequestMapping("/api/site/subject")
public class ApiSubjectController {

    @Resource
    private SubjectService subjectService;

    @ApiOperation("获取分类数据")
    @GetMapping("nested-list")
    public Result getNestedList(){
        List<AnimeSubjectVo> subjectVos = subjectService.nestedAnimeSubjectList();
        return Result.ok().setData("items", subjectVos).setMessage("获取成功");
    }
}
