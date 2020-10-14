package com.github.cccy0.mall.dto;

import com.github.cccy0.mall.model.UmsMenu;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 后台菜单节点封装
 * @author Zhai
 * 2020/10/15 0:04
 */

public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;

    public List<UmsMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsMenuNode> children) {
        this.children = children;
    }
}
