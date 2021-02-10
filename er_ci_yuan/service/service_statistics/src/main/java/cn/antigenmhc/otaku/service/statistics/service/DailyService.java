package cn.antigenmhc.otaku.service.statistics.service;

import cn.antigenmhc.otaku.service.statistics.pojo.Daily;
import cn.antigenmhc.otaku.service.statistics.pojo.vo.ChartVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
public interface DailyService extends IService<Daily> {

    void saveStatisticsByDay(String day);

    Map<String, Map<String, Object>> getCharts(String startTime, String endTime);

    ChartVo getRegisterChart(String startTime, String endTime);
}
