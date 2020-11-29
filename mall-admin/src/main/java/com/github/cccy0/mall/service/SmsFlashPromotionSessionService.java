package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.SmsFlashPromotionSessionDetail;
import com.github.cccy0.mall.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * 限时购场次管理Service
 *
 * @author cy
 */
public interface SmsFlashPromotionSessionService {
    /**
     * 添加场次
     * @param promotionSession promotionSession
     * @return int
     */
    int create(SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次
     * @param id id
     * @param promotionSession promotionSession
     * @return int
     */
    int update(Long id, SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次启用状态
     * @param id id
     * @param status status
     * @return int
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除场次
     * @param id id
     * @return int
     */
    int delete(Long id);

    /**
     * 获取详情
     * @param id id
     * @return SmsFlashPromotionSession
     */
    SmsFlashPromotionSession getItem(Long id);

    /**
     * 根据启用状态获取场次列表
     * @return List
     */
    List<SmsFlashPromotionSession> list();

    /**
     * 获取全部可选场次及其数量
     * @param flashPromotionId flashPromotionId
     * @return List
     */
    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);
}
