package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.dao.UmsRoleDao;
import com.github.cccy0.mall.dao.UmsRolePermissionRelationDao;
import com.github.cccy0.mall.mapper.UmsRoleMapper;
import com.github.cccy0.mall.mapper.UmsRoleMenuRelationMapper;
import com.github.cccy0.mall.mapper.UmsRolePermissionRelationMapper;
import com.github.cccy0.mall.mapper.UmsRoleResourceRelationMapper;
import com.github.cccy0.mall.model.*;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台角色管理Service实现类
 *
 * @author Zhai
 * 2020/9/28 23:28
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    private UmsRoleMapper umsRoleMapper;
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;
    private UmsRolePermissionRelationDao umsRolePermissionRelationDao;
    private UmsRoleDao umsRoleDao;
    private UmsAdminCacheService umsAdminCacheService;

    // region 注入

    @Autowired
    public void setUmsRoleMapper(UmsRoleMapper umsRoleMapper) {
        this.umsRoleMapper = umsRoleMapper;
    }

    @Autowired
    public void setUmsRolePermissionRelationMapper(UmsRolePermissionRelationMapper umsRolePermissionRelationMapper) {
        this.umsRolePermissionRelationMapper = umsRolePermissionRelationMapper;
    }

    @Autowired
    public void setUmsRoleMenuRelationMapper(UmsRoleMenuRelationMapper umsRoleMenuRelationMapper) {
        this.umsRoleMenuRelationMapper = umsRoleMenuRelationMapper;
    }

    @Autowired
    public void setUmsRoleResourceRelationMapper(UmsRoleResourceRelationMapper umsRoleResourceRelationMapper) {
        this.umsRoleResourceRelationMapper = umsRoleResourceRelationMapper;
    }

    @Autowired
    public void setUmsRolePermissionRelationDao(UmsRolePermissionRelationDao umsRolePermissionRelationDao) {
        this.umsRolePermissionRelationDao = umsRolePermissionRelationDao;
    }

    @Autowired
    public void setUmsRoleDao(UmsRoleDao umsRoleDao) {
        this.umsRoleDao = umsRoleDao;
    }

    @Autowired
    public void setUmsAdminCacheService(UmsAdminCacheService umsAdminCacheService) {
        this.umsAdminCacheService = umsAdminCacheService;
    }

    // endregion

    @Override
    public int create(UmsRole umsRole) {
        umsRole.setCreateTime(new Date());
        umsRole.setAdminCount(0);
        umsRole.setSort(0);
        return umsRoleMapper.insert(umsRole);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return umsRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        int count = umsRoleMapper.deleteByExample(example);
        umsAdminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return umsRolePermissionRelationDao.getPermissionList(roleId);
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        // 删除原关系
        UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        ;
        umsRolePermissionRelationMapper.deleteByExample(example);
        // 插入新关系
        List<UmsRolePermissionRelation> relations = permissionIds.stream().map((permissionId) -> {
            UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return umsRolePermissionRelationDao.insertList(relations);
    }

    @Override
    public List<UmsRole> list() {
        return umsRoleMapper.selectByExample(null);
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return umsRoleMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return umsRoleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return umsRoleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return umsRoleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 删除原关系
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        umsRoleMenuRelationMapper.deleteByExample(example);
        // 插入新关系
        menuIds.forEach((menuId) -> {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            umsRoleMenuRelationMapper.insert(relation);
        });
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 删除原关系
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        umsRoleResourceRelationMapper.deleteByExample(example);
        // 插入新关系
        resourceIds.forEach((resourceId) -> {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            umsRoleResourceRelationMapper.insert(relation);
        });
        return resourceIds.size();
    }
}
