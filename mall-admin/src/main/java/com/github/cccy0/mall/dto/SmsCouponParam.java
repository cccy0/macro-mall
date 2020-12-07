package com.github.cccy0.mall.dto;

import com.github.cccy0.mall.model.SmsCoupon;
import com.github.cccy0.mall.model.SmsCouponProductCategoryRelation;
import com.github.cccy0.mall.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和绑定分类
 *
 * @author cccy
 * @date 2018/8/28
 */
public class SmsCouponParam extends SmsCoupon {
    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;
    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;
}
