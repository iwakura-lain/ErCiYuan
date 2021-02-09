package cn.antigenmhc.otaku.service.cms.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.pojo.AdType;
import cn.antigenmhc.otaku.service.cms.service.AdTypeService;
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
 *  推荐位 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@Api(description = "推荐类别管理")
@RestController
@RequestMapping("/admin/cms/ad-type")
@Slf4j
public class AdTypeController {

    @Resource
    private AdTypeService adTypeService;

    @ApiOperation("所有推荐类别列表")
    @GetMapping("list")
    public Result listAll() {
        List<AdType> list = adTypeService.list();
        return Result.ok().setData("items", list);
    }

    @ApiOperation("推荐类别分页列表")
    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit) {

        Page<AdType> pageParam = new Page<>(page, limit);
        IPage<AdType> pageModel = adTypeService.page(pageParam);
        List<AdType> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return Result.ok().setData("total", total).setData("rows", records);
    }

    @ApiOperation(value = "根据ID删除推荐类别")
    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {

        boolean result = adTypeService.removeById(id);
        if (result) {
            return Result.ok().setMessage("删除成功");
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("新增推荐类别")
    @PostMapping("save")
    public Result save(@ApiParam(value = "推荐类别对象", required = true) @RequestBody AdType adType) {

        boolean result = adTypeService.save(adType);
        if (result) {
            return Result.ok().setMessage("保存成功");
        } else {
            return Result.error().setMessage("保存失败");
        }
    }

    @ApiOperation("更新推荐类别")
    @PutMapping("update")
    public Result updateById(@ApiParam(value = "推荐类别对象", required = true) @RequestBody AdType adType) {
        boolean result = adTypeService.updateById(adType);
        if (result) {
            return Result.ok().setMessage("修改成功");
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }

    @ApiOperation("根据id获取推荐类别信息")
    @GetMapping("get/{id}")
    public Result getById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {
        AdType adType = adTypeService.getById(id);
        if (adType != null) {
            return Result.ok().setData("item", adType);
        } else {
            return Result.error().setMessage("数据不存在");
        }
    }
}

