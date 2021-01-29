package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import cn.antigenmhc.otaku.service.manager.pojo.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动漫 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface AnimeService extends IService<Anime> {

    /**
     * 保存 / 更新动漫基本信息
     * @param animeInfoForm：表单对象
     * @param saveOrUpdate ：标志位，保存 or 更新
     * @return ：保存后的动漫记录的 id，用于关联前端中下一步要上传的视频
     */
    String saveOrUpdateAnimeInfo(AnimeInfoForm animeInfoForm, int saveOrUpdate);

    /**
     * 根据 id 获取动漫表单，需要组装 description 和 anime 内容
     * @param id：anime id
     * @return ：anime 表单对象
     */
    AnimeInfoForm getAnimeFormById(String id);

    /**
     * anime 分页方法
     * @param adminPage：分页对象
     * @param queryVo：查询条件
     * @return ： 分页结果
     */
    IPage<AnimeVo> selectPageByQuery(Page<AnimeVo> adminPage, AnimeQueryVo queryVo);

    /**
     * 删除封面
     * @param id: anime id
     * @return ：oss文件是否删除成
     */
    boolean deleteCoverByAdminId(String id);

    /**
     * 删除动漫信息及其所有关联的信息
     * @param id：anime id
     * @return ：是否删除成功
     */
    boolean deleteAnimeAllInfoById(String id);

    /**
     * 组装动漫发布页面的动漫信息
     * @param id：动漫 id
     * @return ：信息对象
     */
    AnimePublishVo getAnimePublishInfoById(String id);

    /**
     * 根据 id 发布动漫
     * @param id：id
     * @return ：是否发布成功
     */
    boolean publishAnimeInfoById(String id);

    List<Map<String, String>> getRecordsNameByKey(String key);

    /**
     * 根据 anime id 删除所有属于该 id 的 chapter 以及 video
     * @param id：anime id
     * @return ：删除状态
     */
    boolean deleteAllChapterAndAllVideoByAnimeId(String id);

    /**
     * 根据查询条件返回动漫列表
     * @param siteAnimeQueryVo：查询条件封装的对象
     * @return ：动漫列表
     */
    List<Anime> siteSelectAnimeListByQuery(SiteAnimeQueryVo siteAnimeQueryVo);

    /**
     * 前台动漫展示分页
     * @param animePage：分页对象
     * @param queryVo：筛选条件对象
     * @return ：分页结果
     */
    IPage<Anime> siteSelectPageByQuery(Page<Anime> animePage, SiteAnimeQueryVo queryVo);

    /**
     * 返回 anime 详细数据，并更新浏览量
     * @param animeId：anime id
     * @return ：详细数据
     */
    SiteAnimeInfoVo siteSelectAnimeInfoAndUpdateViewCount(String animeId);

    /**
     * 获取在首页展示的动漫列表
     * @return ：返回数据，小于 8 条则返回去全部，大于 8 条则返回 top8
     */
    List<Anime> getAnimeListToIndex();
}
