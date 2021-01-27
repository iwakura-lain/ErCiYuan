package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AdminQueryVo;
import cn.antigenmhc.otaku.service.manager.pojo.vo.SiteAdminInfoVo;
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
     * @return ：最终分页对象
     */
    IPage<Admin> selectPageByQuery(Page<Admin> adminPage, AdminQueryVo queryVo);

    /**
     * 根据关键字模糊查询 name
     * @param key：关键字
     * @return ：模糊查询出的名字列表，元素为键值对
     */
    List<Map<String, String>> getRecordsNameByKey(String key);

    /**
     * 根据 admin 的 id 调用远程接口并将对应的在 oss 中的图片进行删除
     * @param id：admin id
     * @return ：能否删除
     */
    boolean deleteAvatarByAdminId(String id);

    /**
     * 批量删除 amdin 时调用远程接口批量删除 oss 中关联文件
     * @param ids：admin id 列表
     * @return：删除是否成功
     */
    boolean deleteAvatarByAdminIds(List<String> ids);

    /**
     * 判断数据库中与待插入 admin 是否有重名的
     * @param name：名字
     * @return ：是否和数据库中数据重复
     */
    boolean hasEqualsName(String name);

    /**
     * 根据 id 组装前端要展示的 Admin 的信息
     * @param id：admin id
     * @return ：组装后的对象
     */
    SiteAdminInfoVo getSiteAdminInfoById(String id);
}
