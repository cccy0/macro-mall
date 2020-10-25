package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.dto.UmsPermissionNode;
import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 *
 * @author Zhai
 * 2020/10/25 23:53
 */
@RestController
@Api(tags = "后台用户权限管理")
@RequestMapping("/permission")
public class UmsPermissionController {
    private UmsPermissionService umsPermissionService;

    @Autowired
    public void setUmsPermissionService(UmsPermissionService umsPermissionService) {
        this.umsPermissionService = umsPermissionService;
    }

    @ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<Integer> create(@RequestBody UmsPermission permission) {
        int count = umsPermissionService.create(permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = umsPermissionService.update(id, permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult<Integer> delete(@RequestParam("ids") List<Long> ids) {
        int count = umsPermissionService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> permissionNodeList = umsPermissionService.treeList();
        return CommonResult.success(permissionNodeList);
    }

    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> list() {
        List<UmsPermission> permissionList = umsPermissionService.list();
        return CommonResult.success(permissionList);
    }
}
