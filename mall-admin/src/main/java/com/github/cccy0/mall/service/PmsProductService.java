package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.PmsProductParam;
import com.github.cccy0.mall.dto.PmsProductQueryParam;
import com.github.cccy0.mall.dto.PmsProductResult;
import com.github.cccy0.mall.model.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品管理Service
 *
 * @author macro
 */
public interface PmsProductService {
    /**
     * 创建商品
     *
     * @param productParam productParam
     * @return int
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int create(PmsProductParam productParam);

    /**
     * 根据商品编号获取更新信息
     *
     * @param id id
     * @return PmsProductResult
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 更新商品
     *
     * @param id           id
     * @param productParam productParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int update(Long id, PmsProductParam productParam);

    /**
     * 分页查询商品
     *
     * @param productQueryParam productQueryParam
     * @param pageSize          pageSize
     * @param pageNum           pageNum
     * @return List
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量修改审核状态
     *
     * @param ids          产品id
     * @param verifyStatus 审核状态
     * @param detail       审核详情
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 批量修改商品上架状态
     *
     * @param ids           ids
     * @param publishStatus publishStatus
     * @return int
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量修改商品推荐状态
     *
     * @param ids             ids
     * @param recommendStatus recommendStatus
     * @return int
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     *
     * @param ids       ids
     * @param newStatus newStatus
     * @return int
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     *
     * @param ids          ids
     * @param deleteStatus deleteStatus
     * @return int
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品名称或者货号模糊查询
     *
     * @param keyword keyword
     * @return List
     */
    List<PmsProduct> list(String keyword);
}
