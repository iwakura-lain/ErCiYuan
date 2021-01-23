package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Chapter;
import cn.antigenmhc.otaku.service.manager.pojo.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 动漫 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 删除 chapter 及其相关联的 video 表中的信息
     * @param id：chapter id
     * @return ：删除是否成功
     */
    boolean removeChapterAndVideoById(String id);

    /**
     * 组装动漫目录
     * @param animeId：动漫 id
     * @return ：一级目录列表(季)，其中每个元素嵌套了一个子列表
     */
    List<ChapterVo> getNestedList(String animeId);

    /**
     * 删除季度下的视频以及视频信息
     * @param id：季度id
     * @return ：删除是否成功
     */
    boolean deleteAllVodByChapterId(String id);
}
