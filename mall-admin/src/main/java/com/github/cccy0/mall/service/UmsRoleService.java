package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.UmsMenu;
import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * @author Zhai
 * 2020/9/28 23:22
 */

public interface UmsRoleService {

    /**
     * 添加角色
     * @param umsRole umsRole
     * @return 影响行数
     */
    int create(UmsRole umsRole);

    /**
     *  修改角色信息
     * @param id id
     * @param role role
     * @return 影响行数
     */
    int update(Long id, UmsRole role);

    /**
     * 批量删除角色
     * @param ids ids
     * @return 影响行数
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     * @param roleId roleId
     * @return 权限
     */
    List<UmsPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     * @param roleId roleId
     * @param permissionIds permissionIds
     * @return 影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取所有角色列表
     * @return 角色列表
     */
    List<UmsRole> list();

    /**
     * 分页获取角色列表
     * @param keyword keyword
     * @param pageSize pageSize
     * @param pageNum pageNum
     * @return 角色列表
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     * @param adminId adminId
     * @return 菜单
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     * @param roleId roleId
     * @return 菜单
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     * @param roleId roleId
     * @return 资源
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     * @param roleId roleId
     * @param menuIds menuIds
     * @return 影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     * @param roleId roleId
     * @param resourceIds resourceIds
     * @return 影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int allocResource(Long roleId, List<Long> resourceIds);
}
