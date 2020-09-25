package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.UmsAdminRoleRelation;
import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台用户与角色管理Dao
 *
 * @author Zhai
 * 2020/9/25 22:08
 */

public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入用户角色关系
     *
     * @param adminRoleRelationList adminRoleRelationList
     * @return 影响行数
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用户所有角色
     *
     * @param adminId adminId
     * @return 角色列表
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     *
     * @param adminId adminId
     * @return 权限列表
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     *
     * @param adminId adminId
     * @return 权限列表
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     *
     * @param adminId adminId
     * @return 资源列表
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     * @param resourceId resourceId
     * @return 用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
