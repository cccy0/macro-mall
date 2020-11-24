package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.SmsHomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页品牌管理Service
 *
 * @author cy
 */
public interface SmsHomeBrandService {
    /**
     * 添加首页品牌推荐
     *
     * @param homeBrandList homeBrandList
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    int create(List<SmsHomeBrand> homeBrandList);

    /**
     * 修改品牌推荐排序
     *
     * @param id   id
     * @param sort sort
     * @return int
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除品牌推荐
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
     * 分页查询品牌推荐
     *
     * @param brandName       brandName
     * @param recommendStatus recommendStatus
     * @param pageSize        pageSize
     * @param pageNum         pageNum
     * @return List
     */
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
