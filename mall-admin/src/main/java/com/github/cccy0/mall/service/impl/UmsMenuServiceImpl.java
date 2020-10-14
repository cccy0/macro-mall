package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.dto.UmsMenuNode;
import com.github.cccy0.mall.mapper.UmsMenuMapper;
import com.github.cccy0.mall.model.UmsMenu;
import com.github.cccy0.mall.model.UmsMenuExample;
import com.github.cccy0.mall.service.UmsMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhai
 * 2020/10/15 0:07
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    private UmsMenuMapper umsMenuMapper;

    @Autowired
    public void setUmsMenuMapper(UmsMenuMapper umsMenuMapper) {
        this.umsMenuMapper = umsMenuMapper;
    }

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return umsMenuMapper.insert(umsMenu);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenu parentMenu = umsMenuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return umsMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return umsMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return umsMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample example = new UmsMenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return umsMenuMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = umsMenuMapper.selectByExample(new UmsMenuExample());
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return umsMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }
}
