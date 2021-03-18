package cn.antigenmhc.otaku.service.acl.service.impl;

import cn.antigenmhc.otaku.service.acl.utils.MenuUtil;
import cn.antigenmhc.otaku.service.acl.mapper.PermissionMapper;
import cn.antigenmhc.otaku.service.acl.pojo.Permission;
import cn.antigenmhc.otaku.service.acl.pojo.RolePermission;
import cn.antigenmhc.otaku.service.acl.pojo.User;
import cn.antigenmhc.otaku.service.acl.service.PermissionService;
import cn.antigenmhc.otaku.service.acl.service.RolePermissionService;
import cn.antigenmhc.otaku.service.acl.service.UserService;
import cn.antigenmhc.otaku.service.acl.utils.PermissionUtil;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private RolePermissionService rolePermissionService;
    
    @Resource
    private UserService userService;
    
    @Override
    public List<Permission> queryAllMenu() {

        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Permission> permissionList = baseMapper.selectList(wrapper);

        //递归创建菜单
        return PermissionUtil.build(permissionList);
    }

    @Override
    public List<Permission> selectAllMenu(String roleId) {
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id",roleId));
        for (Permission permission : allPermissionList) {
            for (RolePermission rolePermission : rolePermissionList) {
                if (rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }

        return PermissionUtil.build(allPermissionList);
    }

    @Override
    public void saveRolePermissionRelationShip(String roleId, String[] permissionIds) {

        //清除原本的角色-权限关系
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<RolePermission>().eq("role_id", roleId);
        rolePermissionService.remove(queryWrapper);

        //保存新的角色-权限关系
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for(String permissionId : permissionIds) {
            if(StringUtils.isEmpty(permissionId)) {
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissionList);
    }

    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        //通过传入的 id 以及空列表，最终将要删除的权限的子权限 id 都加入到该列表中
        this.selectChildListById(id, idList);
        //再加入父权限 id
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionUtil.build(selectPermissionList);
        return MenuUtil.bulid(permissionList);
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        return null != user && "admin".equals(user.getUsername());
    }

    /**
     *	递归获取子节点
     * @param id：父权限 id
     * @param idList：最终存储所有要删除的权限的 id 列表
     */
    private void selectChildListById(String id, List<String> idList) {
        // 模拟栈，层序遍历
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst(id);
        while(!deque.isEmpty()){
            int size = deque.size();
            List<String> child = new LinkedList<>();
            while(size > 0){
                String root = deque.pollFirst();
                //获取当前 root 下的所有子权限
                QueryWrapper<Permission> children = new QueryWrapper<Permission>().eq("pid", root).select("id", "pid");
                List<Permission> childList = baseMapper.selectList(children);
                //添加所有子权限 id 到栈和当前 child 中
                childList.forEach(item ->{
                    child.add(item.getId());
                    deque.addFirst(item.getId());
                });
                size--;
            }
            //添加当前层次的所有权限到 IdList 中，以供删除
            idList.addAll(child);
        }
    }
}
