package com.github.cccy0.mall.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.github.cccy0.mall.common.api.CommonResult;
import com.github.cccy0.mall.dto.UmsAdminLoginParam;
import com.github.cccy0.mall.dto.UmsAdminParam;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsRole;
import com.github.cccy0.mall.service.UmsAdminService;
import com.github.cccy0.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zhai
 * 2020/9/26 16:53
 */
@RestController
@RequestMapping(path = "/admin")
@Api(tags = "后台用户管理")
public class UmsAdminController {
    private UmsAdminService umsAdminService;
    private UmsRoleService umsRoleService;

    @Value("${settings.jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${settings.jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public void setUmsAdminService(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Autowired
    public void setUmsRoleService(UmsRoleService umsRoleService) {
        this.umsRoleService = umsRoleService;
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

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public CommonResult<Map<String, String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsAdminService.refreshToken(token);
        if (StrUtil.isBlank(refreshToken)) {
            return CommonResult.failed("token已经过期!");
        }
        Map<String, String> tokenMap = MapUtil.newHashMap(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult<Map<String, Object>> getAdminInfo(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(username);
        HashMap<String, Object> data = MapUtil.newHashMap(4);
        data.put("username", umsAdmin.getUsername());
        data.put("menus", umsRoleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roles = umsAdminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roles)) {
            List<String> roleStrings = roles.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", roleStrings);
        }
        return CommonResult.success(data);
    }
}
