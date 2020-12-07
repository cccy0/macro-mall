package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.SmsCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠券和商品分类关系管理Dao
 *
 * @author cy
 */
public interface SmsCouponProductCategoryRelationDao {
    /**
     * 批量创建
     *
     * @param productCategoryRelationList productCategoryRelationList
     * @return int
     */
    int insertList(@Param("list") List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
