package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员价格Dao
 *
 * @author macro
 * @date 2018/4/26
 */
public interface PmsMemberPriceDao {
    /**
     * 批量创建
     *
     * @param memberPriceList memberPriceList
     * @return int
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
