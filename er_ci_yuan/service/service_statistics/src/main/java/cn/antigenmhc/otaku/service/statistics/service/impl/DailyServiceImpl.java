package cn.antigenmhc.otaku.service.statistics.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.statistics.pojo.Daily;
import cn.antigenmhc.otaku.service.statistics.mapper.DailyMapper;
import cn.antigenmhc.otaku.service.statistics.remote.RemoteUcenterService;
import cn.antigenmhc.otaku.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

        //获取用户注册数
        Result result = remoteUcenterService.getRegisterCount(day);
        int registerCount = (Integer)result.getData().get("registerCount");

        //模拟新增动漫数
        int animeCount = RandomUtils.nextInt(100, 200);

        Daily daily = new Daily();
        daily.setAnimeNum(animeCount);
        daily.setRegisterNum(registerCount);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }
}
