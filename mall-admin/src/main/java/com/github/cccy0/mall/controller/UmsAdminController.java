package com.github.cccy0.mall.controller;

import cn.hutool.core.map.MapUtil;
import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.dto.UmsAdminLoginParam;
import com.github.cccy0.mall.dto.UmsAdminParam;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zhai
 * 2020/9/26 16:53
 */
@RestController
@RequestMapping(path = "/admin")
@Api(tags = "后台用户管理")
public class UmsAdminController {
    private UmsAdminService umsAdminService;
//    private UmsRoleS
    @Value("${settings.jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${settings.jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public void setUmsAdminService(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = MapUtil.newHashMap(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
