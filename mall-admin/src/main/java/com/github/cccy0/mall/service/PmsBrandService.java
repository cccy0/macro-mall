package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.PmsBrandParam;
import com.github.cccy0.mall.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品品牌Service
 *
 * @author cy
 */
public interface PmsBrandService {
    /**
     * 获取所有品牌
     *
     * @return List
     */
    List<PmsBrand> listAllBrand();

    /**
     * 创建品牌
     *
     * @param pmsBrandParam pmsBrandParam
     * @return int
     */
    int createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 修改品牌
     *
     * @param id            id
     * @param pmsBrandParam pmsBrandParam
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     *
     * @param id id
     * @return int
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     *
     * @param ids ids
     * @return int
     */
    int deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     *
     * @param keyword  keyword
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return List
     */
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 获取品牌
     *
     * @param id id
     * @return PmsBrand
     */
    PmsBrand getBrand(Long id);

    /**
     * 修改显示状态
     *
     * @param ids        ids
     * @param showStatus showStatus
     * @return int
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 修改厂家制造商状态
     *
     * @param ids           ids
     * @param factoryStatus factoryStatus
     * @return int
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
