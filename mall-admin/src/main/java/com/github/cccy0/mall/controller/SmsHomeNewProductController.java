package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo: 首页新品管理Controller
 *
 * @author Zhai
 * 2020/11/19 22:27
 */

@RestController
@Api(tags = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
    private SmsHomeNewProductService homeNewProductService;

    @Autowired
    public void setHomeNewProductService(SmsHomeNewProductService homeNewProductService) {
        this.homeNewProductService = homeNewProductService;
    }
}
