package cn.antigenmhc.otaku.service.statistics.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.statistics.pojo.vo.ChartVo;
import cn.antigenmhc.otaku.service.statistics.service.DailyService;
import com.aliyuncs.ecs.model.v20140526.StartInstanceRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@Api(description = "统计管理")
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyController {

    @Resource
    private DailyService dailyService;

    @ApiOperation("生成统计信息")
    @PostMapping("create/{day}")
    public Result createStatisticsByDay(@PathVariable("day") String day){
        dailyService.saveStatisticsByDay(day);
        return Result.ok().setMessage("生成成功");
    }

    @GetMapping("get-charts/{startTime}/{endTime}")
    public Result getCharts(@PathVariable("startTime") String startTime,
                            @PathVariable("endTime") String endTime){
        Map<String, Map<String, Object>> resMap = dailyService.getCharts(startTime, endTime);
        ChartVo registerChart = dailyService.getRegisterChart(startTime, endTime);
        return Result.ok().setData("chartData", resMap).setData("registerChart", registerChart);
    }
}

