package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义商品管理Dao
 *
 * @author cy
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     *
     * @param id id
     * @return PmsProductResult
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
