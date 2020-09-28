package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.UmsMenu;
import com.github.cccy0.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台角色管理Dao
 * @author Zhai
 * 2020/9/28 23:59
 */

public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     * @param adminId adminId
     * @return 菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取菜单
     * @param roleId roleId
     * @return 菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID获取资源
     * @param roleId roleId
     * @return 资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
