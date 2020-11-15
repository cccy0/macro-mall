package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     * @return List
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     * @param umsResourceCategory umsResourceCategory
     * @return int
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     * @param id id
     * @param umsResourceCategory umsResourceCategory
     * @return int
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     * @param id id
     * @return int
     */
    int delete(Long id);
}
