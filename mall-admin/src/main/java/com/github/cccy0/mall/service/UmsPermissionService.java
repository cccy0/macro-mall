package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.UmsPermissionNode;
import com.github.cccy0.mall.model.UmsPermission;

import java.util.List;

/**
 * @author Zhai
 * 2020/10/25 23:54
 */

public interface UmsPermissionService {
    /**
     * 添加权限
     * @param permission permission
     * @return int
     */
    int create(UmsPermission permission);

    /**
     * 修改权限
     * @param id id
     * @param permission permission
     * @return int
     */
    int update(Long id,UmsPermission permission);

    /**
     * 批量删除权限
     * @param ids ids
     * @return int
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     * @return List
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     * @return List
     */
    List<UmsPermission> list();
}
