package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.SmsCouponParam;
import com.github.cccy0.mall.model.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券管理Service
 *
 * @author cy
 */
public interface SmsCouponService {
    /**
     * 添加优惠券
     *
     * @param couponParam couponParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int create(SmsCouponParam couponParam);

    /**
     * 根据优惠券id删除优惠券
     *
     * @param id id
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     *
     * @param id          id
     * @param couponParam couponParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int update(Long id, SmsCouponParam couponParam);

    /**
     * 分页获取优惠券列表
     *
     * @param name     name
     * @param type     type
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return List
     */
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     *
     * @param id 优惠券表id
     * @return SmsCouponParam
     */
    SmsCouponParam getItem(Long id);
}
