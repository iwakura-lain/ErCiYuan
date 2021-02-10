package cn.antigenmhc.otaku.service.acl.service.impl;

import cn.antigenmhc.otaku.service.acl.pojo.Role;
import cn.antigenmhc.otaku.service.acl.pojo.User;
import cn.antigenmhc.otaku.service.acl.service.AclIndexService;
import cn.antigenmhc.otaku.service.acl.service.PermissionService;
import cn.antigenmhc.otaku.service.acl.service.RoleService;
import cn.antigenmhc.otaku.service.acl.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AclIndexServiceImpl implements AclIndexService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 根据用户名获取用户登录信息
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (null == user) {

        }

        //根据用户id获取角色
        List<Role> roleList = roleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错; 如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/thumb-1920-688669.png");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.selectByUsername(username);

        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }


}
