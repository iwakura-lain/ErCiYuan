package cn.antigenmhc.otaku.service.manager.mapper;

import cn.antigenmhc.otaku.service.manager.pojo.AnimeCollect;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeCollectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 动漫收藏 Mapper 接口
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface AnimeCollectMapper extends BaseMapper<AnimeCollect> {

    List<AnimeCollectVo> getAnimeCollectList(String memberId);
}
