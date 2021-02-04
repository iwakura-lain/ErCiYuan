package cn.antigenmhc.otaku.service.manager.controller.site_api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.vo.SiteAdminInfoVo;
import cn.antigenmhc.otaku.service.manager.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/24 19:42
 * @Version: 1.0
 **/
@Api(description = "前台制作人展示")
@RestController
@RequestMapping("/api/site/admin")
public class ApiAdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("制作方列表展示")
    @GetMapping("list")
    public Result getAll(){
        List<Admin> admins = adminService.list();
        return Result.ok().setData("items", admins).setMessage("获取制作方列表成功");
    }

    @ApiOperation("制作方详细数据")
    @GetMapping("details/{id}")
    public Result getAdminDetails(@PathVariable("id") String id){
        SiteAdminInfoVo siteAdminInfoById = adminService.getSiteAdminInfoById(id);
        return Result.ok().setData("item", siteAdminInfoById).setMessage("获取详细信息成功");
    }

}
