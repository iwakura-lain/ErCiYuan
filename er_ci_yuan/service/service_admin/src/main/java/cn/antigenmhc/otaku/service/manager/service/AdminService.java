package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface AdminService extends IService<Admin> {

    /**
     *根据分页对象以及条件对象进行分页查询
     * @param adminPage：分页对象
     * @param queryVo：条件查询对象
     * @return：最终分页对象
     */
    IPage<Admin> selectPageByQuery(Page<Admin> adminPage, AdminQueryVo queryVo);

    /**
     * 根据关键字模糊查询 name
     * @param key：关键字
     * @return：模糊查询出的名字列表，元素为键值对
     */
    List<Map<String, String>> getRecordsNameByKey(String key);

}
