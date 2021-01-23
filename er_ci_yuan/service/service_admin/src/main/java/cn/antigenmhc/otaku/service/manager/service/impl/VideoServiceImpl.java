package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.manager.pojo.Video;
import cn.antigenmhc.otaku.service.manager.mapper.VideoMapper;
import cn.antigenmhc.otaku.service.manager.remote.RemoteVodFileService;
import cn.antigenmhc.otaku.service.manager.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 动漫视频 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private RemoteVodFileService remoteVodFileService;

    @Override
    public boolean deleteVodByVideoId(String id) {

        Video video = baseMapper.selectById(id);
        String vodId = video.getVideoSourceId();

        Result result = remoteVodFileService.deleteVodFile(vodId);
        return result.getSuccess();
    }
}
