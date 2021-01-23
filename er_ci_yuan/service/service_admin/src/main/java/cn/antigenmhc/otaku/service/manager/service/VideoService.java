package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 动漫视频 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface VideoService extends IService<Video> {
    /**
     * 根据 video id 查询出对应的 vodId 并删除
     * @param id：videoId
     * @return ：删除状态
     */
    boolean deleteVodByVideoId(String id);
}
