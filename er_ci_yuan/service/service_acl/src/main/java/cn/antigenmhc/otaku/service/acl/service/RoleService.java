package cn.antigenmhc.otaku.service.acl.service;

import cn.antigenmhc.otaku.service.acl.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户获取角色数据
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 根据用户分配角色
     */
    void saveUserRoleRelationShip(String userId, String[] roleId);

    List<Role> selectRoleByUserId(String id);
}
