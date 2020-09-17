package com.github.cccy0.mall.demo.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.demo.service.DemoService;
import com.github.cccy0.mall.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    // todo: test query success, need test other
}
