package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.PmsSkuStock;

import java.util.List;

/**
 * sku商品库存管理Service
 *
 * @author cy
 */
public interface PmsSkuStockService {
    /**
     * 根据产品id和skuCode模糊搜索
     *
     * @param pid     pid
     * @param keyword keyword
     * @return List
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     *
     * @param pid          pid
     * @param skuStockList skuStockList
     * @return int
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
