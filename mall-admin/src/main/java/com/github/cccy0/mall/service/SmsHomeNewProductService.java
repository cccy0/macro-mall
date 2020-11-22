package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.SmsHomeNewProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页新品管理Service
 *
 * @author cy
 */
public interface SmsHomeNewProductService {
    /**
     * 添加首页推荐
     *
     * @param homeNewProductList homeNewProductList
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int create(List<SmsHomeNewProduct> homeNewProductList);

    /**
     * 修改推荐排序
     *
     * @param id   id
     * @param sort sort
     * @return int
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     *
     * @param ids ids
     * @return int
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     *
     * @param ids             ids
     * @param recommendStatus recommendStatus
     * @return int
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     *
     * @param productName     productName
     * @param recommendStatus recommendStatus
     * @param pageSize        pageSize
     * @param pageNum         pageNum
     * @return List
     */
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
