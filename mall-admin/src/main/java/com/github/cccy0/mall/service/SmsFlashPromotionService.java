package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.SmsFlashPromotion;

import java.util.List;

/**
 * 限时购活动管理Service
 *
 * @author macro
 * @date 2018/11/16
 */
public interface SmsFlashPromotionService {
    /**
     * 添加活动
     *
     * @param flashPromotion flashPromotion
     * @return int
     */
    int create(SmsFlashPromotion flashPromotion);

    /**
     * 修改指定活动
     *
     * @param id             id
     * @param flashPromotion flashPromotion
     * @return int
     */
    int update(Long id, SmsFlashPromotion flashPromotion);

    /**
     * 删除单个活动
     *
     * @param id id
     * @return int
     */
    int delete(Long id);

    /**
     * 修改上下线状态
     *
     * @param id     id
     * @param status status
     * @return int
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取详细信息
     *
     * @param id id
     * @return SmsFlashPromotion
     */
    SmsFlashPromotion getItem(Long id);

    /**
     * 分页查询活动
     *
     * @param keyword  keyword
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return List
     */
    List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);
}
