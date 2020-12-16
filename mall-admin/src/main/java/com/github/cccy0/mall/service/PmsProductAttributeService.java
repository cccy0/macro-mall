package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.PmsProductAttributeParam;
import com.github.cccy0.mall.dto.ProductAttrInfo;
import com.github.cccy0.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性Service
 *
 * @author cy
 */
public interface PmsProductAttributeService {
    /**
     * 根据分类分页获取商品属性
     *
     * @param cid  分类id
     * @param type 0->属性；2->参数
     * @return List
     */
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     *
     * @param pmsProductAttributeParam pmsProductAttributeParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 修改商品属性
     *
     * @param id                    id
     * @param productAttributeParam productAttributeParam
     * @return int
     */
    int update(Long id, PmsProductAttributeParam productAttributeParam);

    /**
     * 获取单个商品属性信息
     *
     * @param id id
     * @return PmsProductAttribute
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 删除商品属性
     *
     * @param ids ids
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int delete(List<Long> ids);

    /**
     * 获取商品分类对应属性信息
     *
     * @param productCategoryId productCategoryId
     * @return List
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
