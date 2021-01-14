package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Anime;
import cn.antigenmhc.otaku.service.manager.pojo.form.AnimeInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @return：保存后的动漫记录的 id，用于关联前端中下一步要上传的视频
     */
    String saveOrUpdateAnimeInfo(AnimeInfoForm animeInfoForm, int saveOrUpdate);

    /**
     * 根据 id 获取动漫表单，需要组装 description 和 anime 内容
     * @param id：anime id
     * @return：anime 表单对象
     */
    AnimeInfoForm getAnimeFormById(String id);
}
