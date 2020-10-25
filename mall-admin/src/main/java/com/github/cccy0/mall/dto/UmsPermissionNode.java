package com.github.cccy0.mall.dto;

import com.github.cccy0.mall.model.UmsPermission;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 后台权限节点封装
 * @author cy
 */
public class UmsPermissionNode extends UmsPermission {
    @ApiModelProperty(value = "子级权限")
    private List<UmsPermissionNode> children;

    public List<UmsPermissionNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsPermissionNode> children) {
        this.children = children;
    }
}
