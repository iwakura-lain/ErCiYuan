package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.mapper.AnimeDescriptionMapper;
import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.mapper.AnimeMapper;
import cn.antigenmhc.otaku.service.manager.pojo.AnimeDescription;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.service.AnimeDescriptionService;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 动漫 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class AnimeServiceImpl extends ServiceImpl<AnimeMapper, Anime> implements AnimeService {

    @Resource
    private AnimeDescriptionMapper animeDescriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveOrUpdateAnimeInfo(AnimeInfoForm animeInfoForm, int saveOrUpdate) {

        // 保存数据到 anime, 并将基本信息存入数据库
        Anime anime = new Anime();
        BeanUtils.copyProperties(animeInfoForm, anime);
        anime.setStatus(Anime.ANIME_DRAFT);
        anime.setIsDeleted(0);
        // 判断是执行保存还是更新
        if(saveOrUpdate == 0){
            //执行完 insert 方法后，anime 对象中就有 id 了
            baseMapper.insert(anime);
        }else{
            baseMapper.updateById(anime);
        }

        // 保存 desc 到 description
        AnimeDescription animeDescription = new AnimeDescription();
        animeDescription.setDescription(animeInfoForm.getDescription());
        animeDescription.setId(anime.getId());
        if(saveOrUpdate == 0){
            animeDescriptionMapper.insert(animeDescription);
        }else{
            animeDescriptionMapper.updateById(animeDescription);
        }

        return anime.getId();
    }

    @Override
    public AnimeInfoForm getAnimeFormById(String id) {
        AnimeInfoForm resForm = new AnimeInfoForm();
        Anime anime = baseMapper.selectById(id);
        // 如果不存在数据，则直接返回 null
        if(anime == null){
            return null;
        }
        BeanUtils.copyProperties(anime, resForm);

        AnimeDescription animeDescription = animeDescriptionMapper.selectById(id);
        resForm.setDescription(animeDescription.getDescription());
        return resForm;
    }
}
