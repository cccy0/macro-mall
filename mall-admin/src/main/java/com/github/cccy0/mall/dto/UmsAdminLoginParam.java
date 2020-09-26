package com.github.cccy0.mall.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录参数
 * @author Zhai
 * 2020/9/26 16:56
 */
public class UmsAdminLoginParam {
    @NotBlank
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
