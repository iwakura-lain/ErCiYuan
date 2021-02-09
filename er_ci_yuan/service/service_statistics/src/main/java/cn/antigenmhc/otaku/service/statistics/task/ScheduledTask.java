package cn.antigenmhc.otaku.service.statistics.task;

import cn.antigenmhc.otaku.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/7 23:50
 * @Version: 1.0
 **/
@Slf4j
@Component
public class ScheduledTask {
    @Resource
    private DailyService dailyService;

    /**
     * 每天凌晨1点执行定时任务
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void taskGenStatisticsData() {
        //获取上一天的日期
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.saveStatisticsByDay(day);
    }
}
