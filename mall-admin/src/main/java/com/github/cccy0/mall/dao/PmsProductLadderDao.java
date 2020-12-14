package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员阶梯价格Dao
 *
 * @author macro
 * @date 2018/4/26
 */
public interface PmsProductLadderDao {
    /**
     * 批量创建
     *
     * @param productLadderList productLadderList
     * @return int
     */
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
