package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 *
 * @author cy
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 获取包含属性的商品属性分类
     *
     * @return List
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
