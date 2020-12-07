package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.dto.SmsCouponParam;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义优惠券管理Dao
 *
 * @author cy
 */
public interface SmsCouponDao {
    /**
     * 获取优惠券详情包括绑定关系
     *
     * @param id id
     * @return SmsCouponParam
     */
    SmsCouponParam getItem(@Param("id") Long id);
}
