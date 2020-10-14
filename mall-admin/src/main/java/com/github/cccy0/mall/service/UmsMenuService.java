package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.UmsMenuNode;
import com.github.cccy0.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台菜单管理Service
 * @author Zhai
 * 2020/10/15 0:03
 */

public interface UmsMenuService {
    /**
     * 创建后台菜单
     * @param umsMenu umsMenu
     * @return 影响行数
     */
    int create(UmsMenu umsMenu);

    /**
     * 修改后台菜单
     * @param id id
     * @param umsMenu umsMenu
     * @return 影响行数
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据ID获取菜单详情
     * @param id id
     * @return UmsMenu
     */
    UmsMenu getItem(Long id);

    /**
     * 根据ID删除菜单
     * @param id id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     * @param parentId parentId
     * @param pageSize pageSize
     * @param pageNum pageNum
     * @return List
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     * @return List
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     * @param id id
     * @param hidden hidden
     * @return 影响行数
     */
    int updateHidden(Long id, Integer hidden);
}
