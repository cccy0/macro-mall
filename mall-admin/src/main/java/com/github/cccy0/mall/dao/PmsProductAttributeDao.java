package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品属性Dao
 *
 * @author cy
 */
public interface PmsProductAttributeDao {
    /**
     * 获取商品属性信息
     *
     * @param productCategoryId productCategoryId
     * @return List
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
