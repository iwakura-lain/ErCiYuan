package cn.antigenmhc.otaku.service.acl.controller.admin;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.acl.pojo.Permission;
import cn.antigenmhc.otaku.service.acl.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return Result.ok().setData("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId,permissionId);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.ok().setData("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

}

