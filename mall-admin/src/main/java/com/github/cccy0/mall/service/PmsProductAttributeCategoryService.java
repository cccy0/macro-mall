package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.PmsProductAttributeCategoryItem;
import com.github.cccy0.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类Service
 *
 * @author cy
 */
public interface PmsProductAttributeCategoryService {
    /**
     * 创建属性分类
     *
     * @param name name
     * @return int
     */
    int create(String name);

    /**
     * 修改属性分类
     *
     * @param id   id
     * @param name name
     * @return int
     */
    int update(Long id, String name);

    /**
     * 删除属性分类
     *
     * @param id id
     * @return int
     */
    int delete(Long id);

    /**
     * 获取属性分类详情
     *
     * @param id id
     * @return PmsProductAttributeCategory
     */
    PmsProductAttributeCategory getItem(Long id);

    /**
     * 分页查询属性分类
     *
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return List
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取包含属性的属性分类
     *
     * @return List
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
