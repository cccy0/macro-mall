package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.mapper.UmsResourceMapper;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.service.UmsResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhai
 * 2020/9/25 21:45
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    private UmsResourceMapper umsResourceMapper;
    private UmsAdminCache
    @Override
    public int create(UmsResource umsResource) {
        return 0;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        return 0;
    }

    @Override
    public UmsResource getItem(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsResource> listAll() {
        return null;
    }
}
