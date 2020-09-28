package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.model.UmsRolePermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义角色权限关系管理Dao
 * @author Zhai
 * 2020/9/28 23:30
 */

public interface UmsRolePermissionRelationDao {
    /**
     * 批量插入角色和权限关系
     * @param list list
     * @return 影响行数
     */
    int insertList(@Param("list") List<UmsRolePermissionRelation> list);

    /**
     * 根据角色获取权限
     * @param roleId roleId
     * @return 权限
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
}
