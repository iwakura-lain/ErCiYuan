package cn.antigenmhc.otaku.service.acl.utils;


import cn.antigenmhc.otaku.service.acl.pojo.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
public class PermissionUtil {

    /**
     * 使用递归创建菜单
     * @param treeNodes：按照 id 排序后的权限集合
     * @return ：生成的权限菜单树
     */
    public static List<Permission> build(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                //设置顶层菜单 level 为 1
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<>());

        //遍历所有权限
        for (Permission it : treeNodes) {
            //判断当前权限的 Pid 是否等于传进来的权限的 id
            //如果相等，则 treeNode 就是当前权限的父权限
            if(treeNode.getId().equals(it.getPid())) {
                //设置子权限为父权限的 level + 1
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                //如果父权限没有子权限列表，则进行初始化
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                //获取子权限列表，并递归添加其所有的子权限，构成权限树
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        //返回子权限，最终返回最上层子权限
        return treeNode;
    }
}
