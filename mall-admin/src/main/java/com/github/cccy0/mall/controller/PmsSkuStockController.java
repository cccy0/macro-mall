package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sku库存Controller
 *
 * @author Zhai
 * 2020/12/9 1:43
 */
@RestController
@Api(tags = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    private PmsSkuStockService skuStockService;

    @Autowired
    public void setSkuStockService(PmsSkuStockService skuStockService) {
        this.skuStockService = skuStockService;
    }
}
