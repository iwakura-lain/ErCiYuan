package cn.antigenmhc.otaku.service.statistics.service;

import cn.antigenmhc.otaku.service.statistics.pojo.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
