package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠券和商品关系关系Dao
 *
 * @author cy
 */
public interface SmsCouponProductRelationDao {
    /**
     * 批量创建
     *
     * @param productRelationList productRelationList
     * @return int
     */
    int insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}
