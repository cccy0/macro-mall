package com.github.cccy0.mall.demo.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.demo.dto.PmsBrandDto;
import com.github.cccy0.mall.demo.service.DemoService;
import com.github.cccy0.mall.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理示例controller
 * @author Zhai
 * 2020/9/17 22:10
 */
@Api(tags = "品牌管理示例接口")
@RestController
@RequestMapping(path = "/brands")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    private DemoService demoService;

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(demoService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<Object> createBrand(@Validated @RequestBody PmsBrandDto pmsBrand) {
        CommonResult<Object> commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
            logger.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            logger.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<Object> updateBrand(@PathVariable("id") Long id,
                                            @Validated @RequestBody PmsBrandDto pmsBrand) {
        CommonResult<Object> commonResult;
        int count = demoService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
            logger.debug("updateBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            logger.debug("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult<Object> deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            logger.debug("deleteBrand success, id={}", id);
            return CommonResult.success(null);
        } else {
            logger.debug("deleteBrand failed, id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
}
