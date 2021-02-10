package cn.antigenmhc.otaku.service.statistics.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.base.dto.RegisterDto;
import cn.antigenmhc.otaku.service.statistics.pojo.Daily;
import cn.antigenmhc.otaku.service.statistics.mapper.DailyMapper;
import cn.antigenmhc.otaku.service.statistics.pojo.vo.ChartVo;
import cn.antigenmhc.otaku.service.statistics.remote.RemoteUcenterService;
import cn.antigenmhc.otaku.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Resource
    private RemoteUcenterService remoteUcenterService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveStatisticsByDay(String day) {

        //如果已经有数据，则删除后生成新的
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<Daily>().eq("date_calculated", day);
        Daily hasDay = baseMapper.selectOne(queryWrapper);
        if (hasDay != null){
            this.removeById(hasDay);
        }

        //获取注册dto
        RegisterDto registerCount = remoteUcenterService.getRegisterCount(day);

        //模拟动漫数
        int animeCount = RandomUtils.nextInt(100, 200);
        //模拟登录数
        int loginCount = RandomUtils.nextInt(100, 300);
        //模拟播放数
        int viewCount = RandomUtils.nextInt(1_000, 2_000);

        Daily daily = new Daily();
        daily.setVideoViewNum(viewCount);
        daily.setLoginNum(loginCount);
        daily.setAnimeNum(animeCount);
        daily.setRegisterNum(registerCount.getRegisterCount());
        daily.setGiteeNum(registerCount.getGiteeCount());
        daily.setGithubNum(registerCount.getGithubCount());
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Map<String, Object>> getCharts(String startTime, String endTime) {

        Map<String, Object> loginNum = getChartsByType(startTime, endTime, "login_num");
        Map<String, Object> videoViewNum = getChartsByType(startTime, endTime, "video_view_num");
        Map<String, Object> animeNum = getChartsByType(startTime, endTime, "anime_num");

        Map<String, Map<String, Object>> resMap = new HashMap<>();
        resMap.put("loginNum", loginNum);
        resMap.put("videoViewNum", videoViewNum);
        resMap.put("animeNum", animeNum);
        return resMap;
    }

    @Override
    public ChartVo getRegisterChart(String startTime, String endTime) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", startTime, endTime);
        List<Daily> dailyList = baseMapper.selectList(queryWrapper);

        int registerSum = 0, giteeSum = 0, githubSum = 0;
        for (Daily daily : dailyList) {
            registerSum += daily.getRegisterNum();
            giteeSum += daily.getGiteeNum();
            githubSum += daily.getGithubNum();
        }

        ChartVo chartVo = new ChartVo();
        chartVo.setGiteeCount(giteeSum);
        chartVo.setGithubCount(githubSum);
        chartVo.setRegisterCount(registerSum);

        return chartVo;
    }

    private Map<String, Object> getChartsByType(String startTime, String endTime, String type){
        Map<String, Object> map = new HashMap<>();
        List<String> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        //查出x轴，时间轴
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("date_calculated", type);
        queryWrapper.between("date_calculated", startTime, endTime);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);

        for (Map<String, Object> entry : maps) {
            String data = (String) entry.get("date_calculated");
            xList.add(data);

            Integer count = (Integer)entry.get(type);
            yList.add(count);
        }

        map.put("xData", xList);
        map.put("yData", yList);

        return map;
    }
}
