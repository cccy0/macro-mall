package com.github.cccy0.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.cccy0.mall.mapper.UmsResourceMapper;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.model.UmsResourceExample;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Zhai
 * 2020/9/25 21:45
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    private UmsResourceMapper umsResourceMapper;
    private UmsAdminCacheService umsAdminCacheService;

    @Autowired
    public void setUmsResourceMapper(UmsResourceMapper umsResourceMapper) {
        this.umsResourceMapper = umsResourceMapper;
    }

    @Autowired
    public void setUmsAdminCacheService(UmsAdminCacheService umsAdminCacheService) {
        this.umsAdminCacheService = umsAdminCacheService;
    }

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return umsResourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = umsResourceMapper.updateByPrimaryKeySelective(umsResource);
        umsAdminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return umsResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = umsResourceMapper.deleteByPrimaryKey(id);
        umsAdminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotBlank(nameKeyword)) {
            criteria.andNameLike('%' + nameKeyword + '%');
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            criteria.andUrlLike('%' + urlKeyword + '%');
        }
        return umsResourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return umsResourceMapper.selectByExample(null);
    }
}
