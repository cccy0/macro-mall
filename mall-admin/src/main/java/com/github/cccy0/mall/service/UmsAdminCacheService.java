package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsResource;

import java.util.List;

/**
 * 后台用户缓存操作类
 * @author Zhai
 * 2020/9/25 21:47
 */

public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     * @param adminId 用户ID
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     * @param adminId 用户ID
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleId 角色ID
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleIds 角色ID列表
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时, 删除资源项目后台用户缓存
     * @param resourceId 资源ID
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 根据用户名获取后台用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 缓存后台用户信息
     * @param admin 后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 根据用户ID获取后台用户资源列表
     * @param adminId 用户ID
     * @return 用户资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 缓存后台用户资源信息
     * @param adminId 用户ID
     * @param resourceList 用户资源
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
