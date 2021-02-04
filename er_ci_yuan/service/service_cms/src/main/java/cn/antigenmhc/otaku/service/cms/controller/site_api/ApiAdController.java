package cn.antigenmhc.otaku.service.cms.controller.site_api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.pojo.Ad;
import cn.antigenmhc.otaku.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/27 17:55
 * @Version: 1.0
 **/
@Api(description = "推荐展示")
@RestController
@RequestMapping("/api/site/ad")
@Slf4j
public class ApiAdController {

    @Resource
    private AdService adService;

    @ApiOperation("根据推荐位的类型来获取该推荐位下所有推荐")
    @GetMapping("list/{adTypeId}")
    public Result getListByTypeId(@PathVariable("adTypeId") String adTypeId){
        List<Ad> adList = adService.selectListByTypeId(adTypeId);
        return Result.ok().setData("items", adList);
    }
}
