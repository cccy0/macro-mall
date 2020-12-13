package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.model.PmsSkuStock;
import com.github.cccy0.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword", required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    @ApiOperation("批量更新库存信息")
    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    public CommonResult<Integer> update(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList) {
        int count = skuStockService.update(pid, skuStockList);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
