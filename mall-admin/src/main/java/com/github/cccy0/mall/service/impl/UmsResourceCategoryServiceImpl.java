package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.mapper.UmsResourceCategoryMapper;
import com.github.cccy0.mall.model.UmsResourceCategory;
import com.github.cccy0.mall.model.UmsResourceCategoryExample;
import com.github.cccy0.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 *
 * @author cy
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Autowired
    public void setResourceCategoryMapper(UmsResourceCategoryMapper resourceCategoryMapper) {
        this.resourceCategoryMapper = resourceCategoryMapper;
    }

    @Override
    public List<UmsResourceCategory> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return resourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
