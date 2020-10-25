package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.common.api.CommonPage;
import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.dto.UmsMenuNode;
import com.github.cccy0.mall.model.UmsMenu;
import com.github.cccy0.mall.service.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<Integer> create(@RequestBody UmsMenu umsMenu) {
        int count = umsMenuService.create(umsMenu);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<Integer> update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu) {
        int count = umsMenuService.update(id, umsMenu);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取菜单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsMenu> getItem(@PathVariable Long id) {
        UmsMenu umsMenu = umsMenuService.getItem(id);
        return CommonResult.success(umsMenu);
    }

    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = umsMenuService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> menuList = umsMenuService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = umsMenuService.treeList();
        return CommonResult.success(list);
    }

    @ApiOperation("修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    public CommonResult<Integer> updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = umsMenuService.updateHidden(id, hidden);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
