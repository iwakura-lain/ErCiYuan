package cn.antigenmhc.otaku.service.cms.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.pojo.Ad;
import cn.antigenmhc.otaku.service.cms.pojo.vo.AdVo;
import cn.antigenmhc.otaku.service.cms.service.AdService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@Api(description = "推荐管理")
@RestController
@RequestMapping("/admin/cms/ad")
@Slf4j
public class AdController {

    @Resource
    private AdService adService;

    @ApiOperation(value = "根据ID删除推荐")
    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {

        //删除图片
        adService.removeAdImageById(id);

        //删除推荐
        boolean result = adService.removeById(id);
        if (result) {
            return Result.ok().setMessage("删除成功");
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("推荐分页列表")
    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit) {
        Page<AdVo> adminPage = new Page<>(page, limit);
        IPage<AdVo> pageModel = adService.selectPage(adminPage);
        List<AdVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return Result.ok().setData("total", total).setData("rows", records);
    }

    @ApiOperation("新增推荐")
    @PostMapping("save")
    public Result save(@ApiParam(value = "推荐对象", required = true) @RequestBody Ad ad) {

        boolean result = adService.save(ad);
        if (result) {
            return Result.ok().setMessage("保存成功");
        } else {
            return Result.error().setMessage("保存失败");
        }
    }

    @ApiOperation("更新推荐")
    @PutMapping("update")
    public Result updateById(@ApiParam(value = "推荐对象", required = true) @RequestBody Ad ad) {
        boolean result = adService.updateById(ad);
        if (result) {
            return Result.ok().setMessage("修改成功");
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("根据id获取推荐信息")
    @GetMapping("get/{id}")
    public Result getById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {
        Ad ad = adService.getById(id);
        if (ad != null) {
            return Result.ok().setData("item", ad);
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }
}

