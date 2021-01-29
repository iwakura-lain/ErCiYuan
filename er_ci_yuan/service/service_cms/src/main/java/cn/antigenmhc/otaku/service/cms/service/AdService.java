package cn.antigenmhc.otaku.service.cms.service;

import cn.antigenmhc.otaku.service.cms.pojo.Ad;
import cn.antigenmhc.otaku.service.cms.pojo.vo.AdVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
public interface AdService extends IService<Ad> {

    /**
     * 推荐分页
     * @param page ：分页对象
     * @return ：分页结果
     */
    IPage<AdVo> selectPage(Page<AdVo> page);

    /**
     * 删除 ad 信息和 oss 中的图片
     * @param id：ad id
     * @return ：删除结果
     */
    boolean removeAdImageById(String id);

    /**
     * 获取某一推荐类型下所有的推荐
     * @param adTypeId：推荐类型 id
     * @return ：所有的推荐
     */
    List<Ad> selectListByTypeId(String adTypeId);
}
