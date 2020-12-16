package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品分类和属性关系Dao
 *
 * @author cy
 */
public interface PmsProductCategoryAttributeRelationDao {
    /**
     * 批量创建
     *
     * @param productCategoryAttributeRelationList productCategoryAttributeRelationList
     * @return int
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}
