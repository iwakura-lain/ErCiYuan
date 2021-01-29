package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.manager.mapper.*;
import cn.antigenmhc.otaku.service.manager.pojo.*;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.pojo.vo.*;
import cn.antigenmhc.otaku.service.manager.remote.RemoteOssFileService;
import cn.antigenmhc.otaku.service.manager.remote.RemoteVodFileService;
import cn.antigenmhc.otaku.service.manager.service.AnimeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Resource
    private RemoteOssFileService ossFileService;
    @Resource
    private RemoteVodFileService remoteVodFileService;

    @Resource
    private AnimeDescriptionMapper descriptionMapper;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private AnimeCollectMapper animeCollectMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveOrUpdateAnimeInfo(AnimeInfoForm animeInfoForm, int saveOrUpdate) {

        // 保存数据到 anime, 并将基本信息存入数据库
        Anime anime = new Anime();
        BeanUtils.copyProperties(animeInfoForm, anime);
        anime.setStatus(Anime.ANIME_DRAFT);
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

    @Override
    public IPage<AnimeVo> selectPageByQuery(Page<AnimeVo> animePage, AnimeQueryVo queryVo) {
        return baseMapper.selectAnimeByQuery(animePage, queryVo);
    }

    @Override
    public boolean deleteCoverByAdminId(String id) {
        Anime anime = baseMapper.selectById(id);
        if(anime != null){
            String cover = anime.getCover();
            if(!StringUtils.isEmptyOrWhitespaceOnly(cover)){
                Result result = ossFileService.deleteFile(cover);
                return result.getSuccess();
            }
        }
        return false;
    }

    /**
     * 因为不做数据库中的外键关联，所以为了在应用层实现级联删除
     * 因此要先删除子表的数据，再删除父表的数据，避免子表中的数据成为数据孤岛
     * @param id：anime id
     * @return：删除是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAnimeAllInfoById(String id) {
        descriptionMapper.deleteById(id);
        //先删除 video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("anime_id", id);
        videoMapper.delete(videoQueryWrapper);
        //再删 chapter
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("anime_id", id);
        chapterMapper.delete(chapterQueryWrapper);
        //再删 comment
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("anime_id", id);
        commentMapper.delete(commentQueryWrapper);
        //再删 animeCollect
        QueryWrapper<AnimeCollect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("anime_id", id);
        animeCollectMapper.delete(collectQueryWrapper);

        descriptionMapper.deleteById(id);

        return this.removeById(id);
    }

    @Override
    public AnimePublishVo getAnimePublishInfoById(String id) {

        return baseMapper.getAnimePublishInfoById(id);
    }

    @Override
    public boolean publishAnimeInfoById(String id) {

        Anime anime = new Anime();
        anime.setStatus(Anime.ANIME_NORMAL);
        anime.setId(id);
        return this.updateById(anime);
    }

    @Override
    public List<Map<String, String>> getRecordsNameByKey(String key) {
        List<Anime> records = baseMapper.getRecordsNameByKey(key);

        List<Map<String, String>> nameList= new LinkedList<>();

        for (Anime record : records) {
            Map<String, String> tmp = new ConcurrentHashMap<>(1);
            //element-ui 中输入建议默认渲染 key 为 value 的数据，
            //如果想要在 key 为其它值的情况下渲染数据，查询 element-ui 文档中
            // input 组件 -> autoComplete attributes -> value-key
            tmp.put("value", record.getTitle());
            nameList.add(tmp);
        }

        return nameList;
    }

    @Override
    public boolean deleteAllChapterAndAllVideoByAnimeId(String id) {

        //查询出属于 animeId 下的视频，并从远端开始删除，并删除子结点信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("anime_id", id);
        List<Video> videos = videoMapper.selectList(videoQueryWrapper);
        List<String> vodIds = new ArrayList<>();
        List<String> videoIds = new ArrayList<>();
        //获取 vod 视频以及视频id
        videos.forEach(video->{
            vodIds.add(video.getVideoSourceId());
            if(vodIds.size() >= 20){
                remoteVodFileService.deleteVodFile(String.join(",", vodIds));
                vodIds.clear();
            }
            videoIds.add(video.getId());
        });
        //删除
        if(vodIds.size() != 0){
            remoteVodFileService.deleteVodFile(String.join(",", vodIds));
        }
        if(videoIds.size() != 0){
            videoMapper.deleteBatchIds(videoIds);
        }

        //这里不用删除chapter的信息，chapter的信息在删除anime时进行了

        return true;
    }

    @Override
    public List<Anime> siteSelectAnimeListByQuery(SiteAnimeQueryVo siteAnimeQueryVo) {
        return baseMapper.siteSelectAnimeListByQuery(siteAnimeQueryVo);
    }

    @Override
    public IPage<Anime> siteSelectPageByQuery(Page<Anime> animePage, SiteAnimeQueryVo queryVo) {
        return baseMapper.siteSelectPageByQuery(animePage, queryVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SiteAnimeInfoVo siteSelectAnimeInfoAndUpdateViewCount(String animeId) {
        Anime anime = baseMapper.selectById(animeId);
        anime.setViewCount(anime.getViewCount()+1);
        baseMapper.updateById(anime);

        return baseMapper.siteSelectAnimeInfo(animeId);
    }

    @Cacheable(value = "index", key = "'getAnimeListToIndex'")
    @Override
    public List<Anime> getAnimeListToIndex() {
        QueryWrapper<Anime> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.eq("status", Anime.ANIME_NORMAL);
        List<Anime> animeList = baseMapper.selectList(queryWrapper);

        if(animeList.size() <= 8){
            return animeList;
        }else{
            return animeList.subList(0, 8);
        }
    }
}
