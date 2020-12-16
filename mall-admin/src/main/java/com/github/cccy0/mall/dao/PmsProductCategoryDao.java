package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 商品分类自定义Dao
 *
 * @author cy
 */
public interface PmsProductCategoryDao {
    /**
     * 获取商品分类及其子分类
     *
     * @return List
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
