package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.service.UmsMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台菜单管理
 * @author Zhai
 * 2020/10/15 0:01
 */
@RestController
@Api(tags = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {
    private UmsMenuService umsMenuService;

    @Autowired
    public void setUmsMenuService(UmsMenuService umsMenuService) {
        this.umsMenuService = umsMenuService;
    }

    // todo: curd菜单
}
