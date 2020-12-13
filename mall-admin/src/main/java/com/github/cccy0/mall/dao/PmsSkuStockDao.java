package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品SKU管理Dao
 * @author cy
 */
public interface PmsSkuStockDao {
    /**
     * 批量插入操作
     * @param skuStockList skuStockList
     * @return int
     */
    int insertList(@Param("list")List<PmsSkuStock> skuStockList);

    /**
     * 批量插入或替换操作
     * @param skuStockList skuStockList
     * @return int
     */
    int replaceList(@Param("list")List<PmsSkuStock> skuStockList);
}
