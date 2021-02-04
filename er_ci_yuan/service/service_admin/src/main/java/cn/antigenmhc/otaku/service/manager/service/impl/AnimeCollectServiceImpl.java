package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.pojo.AnimeCollect;
import cn.antigenmhc.otaku.service.manager.mapper.AnimeCollectMapper;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeCollectVo;
import cn.antigenmhc.otaku.service.manager.service.AnimeCollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动漫收藏 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class AnimeCollectServiceImpl extends ServiceImpl<AnimeCollectMapper, AnimeCollect> implements AnimeCollectService {

    @Override
    public boolean isCollect(String animeId, String memberId) {
        QueryWrapper<AnimeCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("anime_id", animeId)
                .eq("member_id", memberId);
        return this.getOne(queryWrapper) != null;
    }

    @Override
    public boolean addCollectAnime(String animeId, String memberId) {
        if(isCollect(animeId, memberId)){
            return false;
        }
        AnimeCollect animeCollect = new AnimeCollect();
        animeCollect.setAnimeId(animeId);
        animeCollect.setMemberId(memberId);
        return this.save(animeCollect);
    }

    @Override
    public boolean deleteCollectAnime(String animeId, String memberId) {
        QueryWrapper<AnimeCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("anime_id", animeId)
                .eq("member_id", memberId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<AnimeCollectVo> getAnimeCollectList(String memberId) {
        return baseMapper.getAnimeCollectList(memberId);
    }
}
