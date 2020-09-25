package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理
 * @author Zhai
 * 2020/9/25 21:40
 */

public interface UmsResourceService {

    /**
     * 添加资源
     * @param umsResource umsResource
     * @return 影响行数
     */
    int create(UmsResource umsResource);

    /**
     * 修改资源
     * @param id id
     * @param umsResource umsResource
     * @return 影响行数
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 获取资源详情
     * @param id id
     * @return 资源详情
     */
    UmsResource getItem(Long id);

    /**
     * 删除资源
     * @param id id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 分页查询资源
     * @param categoryId categoryId
     * @param nameKeyword nameKeyword
     * @param urlKeyword urlKeyword
     * @param pageSize pageSize
     * @param pageNum pageNum
     * @return data
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     * @return data
     */
    List<UmsResource> listAll();
}
