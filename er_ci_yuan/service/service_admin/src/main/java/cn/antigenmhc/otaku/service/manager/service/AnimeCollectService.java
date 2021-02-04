package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.AnimeCollect;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeCollectVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 动漫收藏 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface AnimeCollectService extends IService<AnimeCollect> {

    boolean isCollect(String animeId, String memberId);

    boolean addCollectAnime(String animeId, String memberId);

    boolean deleteCollectAnime(String animeId, String memberId);

    List<AnimeCollectVo> getAnimeCollectList(String memberId);
}
