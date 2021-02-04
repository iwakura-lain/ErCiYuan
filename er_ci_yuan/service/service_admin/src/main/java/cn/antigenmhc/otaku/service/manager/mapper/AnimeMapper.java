package cn.antigenmhc.otaku.service.manager.mapper;

import cn.antigenmhc.otaku.service.base.dto.AnimeDto;
import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.vo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 动漫 Mapper 接口
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface AnimeMapper extends BaseMapper<Anime> {

    IPage<AnimeVo> selectAnimeByQuery(Page<?> page, AnimeQueryVo queryVo);

    AnimePublishVo getAnimePublishInfoById(String id);

    List<Anime> getRecordsNameByKey(String key);

    List<Anime> siteSelectAnimeListByQuery(SiteAnimeQueryVo siteAnimeQueryVo);

    IPage<Anime> siteSelectPageByQuery(Page<Anime> animePage, SiteAnimeQueryVo queryVo);

    SiteAnimeInfoVo siteSelectAnimeInfo(String animeId);

    AnimeDto getAnimeDtoByAnimeId(String animeId);
}
