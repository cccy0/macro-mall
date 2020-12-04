package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.mapper.SmsCouponHistoryMapper;
import com.github.cccy0.mall.model.SmsCouponHistory;
import com.github.cccy0.mall.model.SmsCouponHistoryExample;
import com.github.cccy0.mall.service.SmsCouponHistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 优惠券领取记录管理Service实现类
 *
 * @author cy
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    private SmsCouponHistoryMapper historyMapper;

    @Autowired
    public void setHistoryMapper(SmsCouponHistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }

    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if (couponId != null) {
            criteria.andCouponIdEqualTo(couponId);
        }
        if (useStatus != null) {
            criteria.andUseStatusEqualTo(useStatus);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        return historyMapper.selectByExample(example);
    }
}
