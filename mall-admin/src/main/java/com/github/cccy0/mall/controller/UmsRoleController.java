package com.github.cccy0.mall.controller;

import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.model.UmsRole;
import com.github.cccy0.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户角色管理
 * @author Zhai
 * 2020/10/11 23:19
 */
@RestController
@Api(tags = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    private UmsRoleService umsRoleService;

    @Autowired
    public void setUmsRoleService(UmsRoleService umsRoleService) {
        this.umsRoleService = umsRoleService;
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<Integer> create(@RequestBody UmsRole role) {
        int count = umsRoleService.create(role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    // todo: 修改角色
}
