package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.PmsProductCategoryParam;
import com.github.cccy0.mall.dto.PmsProductCategoryWithChildrenItem;
import com.github.cccy0.mall.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类Service
 *
 * @author cy
 */
public interface PmsProductCategoryService {
    /**
     * 创建商品分类
     *
     * @param pmsProductCategoryParam pmsProductCategoryParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int create(PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 修改商品分类
     *
     * @param id                      id
     * @param pmsProductCategoryParam pmsProductCategoryParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品分类
     *
     * @param parentId parentId
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return List
     */
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     *
     * @param id id
     * @return int
     */
    int delete(Long id);

    /**
     * 根据ID获取商品分类
     *
     * @param id id
     * @return PmsProductCategory
     */
    PmsProductCategory getItem(Long id);

    /**
     * 批量修改导航状态
     *
     * @param ids       ids
     * @param navStatus navStatus
     * @return int
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     *
     * @param ids        ids
     * @param showStatus showStatus
     * @return int
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     *
     * @return List
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
