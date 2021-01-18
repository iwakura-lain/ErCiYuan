package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.mapper.VideoMapper;
import cn.antigenmhc.otaku.service.manager.pojo.Chapter;
import cn.antigenmhc.otaku.service.manager.mapper.ChapterMapper;
import cn.antigenmhc.otaku.service.manager.pojo.Video;
import cn.antigenmhc.otaku.service.manager.pojo.vo.ChapterVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.VideoVo;
import cn.antigenmhc.otaku.service.manager.service.ChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 动漫 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoMapper videoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterAndVideoById(String id) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> getNestedList(String animeId) {

        //获取季信息
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("anime_id",animeId);
        chapterQueryWrapper.orderByAsc("gmt_create");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //获取集信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("anime_id",animeId);
        videoQueryWrapper.orderByAsc("gmt_create");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);

        //组装信息
        List<ChapterVo> resList = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            resList.add(chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();
            for (Video video : videoList) {
                VideoVo videoVo = new VideoVo();
                if(video.getChapterId().equals(chapter.getId())){
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildrenVideo(videoVoList);
        }

        return resList;
    }
}
