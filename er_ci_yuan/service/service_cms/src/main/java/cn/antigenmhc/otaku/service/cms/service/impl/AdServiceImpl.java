package cn.antigenmhc.otaku.service.cms.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.cms.pojo.vo.AdVo;
import cn.antigenmhc.otaku.service.cms.pojo.Ad;
import cn.antigenmhc.otaku.service.cms.remote.OssFileService;
import cn.antigenmhc.otaku.service.cms.mapper.AdMapper;
import cn.antigenmhc.otaku.service.cms.service.AdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Resource
    private OssFileService ossFileService;

    @Override
    public IPage<AdVo> selectPage(Page<AdVo> page) {
        return baseMapper.selectPage(page);
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmptyOrWhitespaceOnly(imagesUrl)){
                //删除图片
                Result r = ossFileService.deleteFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Cacheable(value = "index", key = "'selectListByTypeId'")
    @Override
    public List<Ad> selectListByTypeId(String adTypeId) {
        QueryWrapper<Ad> adQueryWrapper = new QueryWrapper<>();
        adQueryWrapper.eq("type_id", adTypeId);
        adQueryWrapper.orderByAsc("sort");
        return baseMapper.selectList(adQueryWrapper);
    }
}
