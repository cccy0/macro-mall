package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.dao.SmsFlashPromotionProductRelationDao;
import com.github.cccy0.mall.dto.SmsFlashPromotionProduct;
import com.github.cccy0.mall.mapper.SmsFlashPromotionProductRelationMapper;
import com.github.cccy0.mall.model.SmsFlashPromotionProductRelation;
import com.github.cccy0.mall.model.SmsFlashPromotionProductRelationExample;
import com.github.cccy0.mall.service.SmsFlashPromotionProductRelationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限时购商品关联管理Service实现类
 *
 * @author cy
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    private SmsFlashPromotionProductRelationMapper relationMapper;
    private SmsFlashPromotionProductRelationDao relationDao;

    @Autowired
    public void setRelationMapper(SmsFlashPromotionProductRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    @Autowired
    public void setRelationDao(SmsFlashPromotionProductRelationDao relationDao) {
        this.relationDao = relationDao;
    }

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            relationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return relationMapper.updateByPrimaryKey(relation);
    }

    @Override
    public int delete(Long id) {
        return relationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return relationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return relationDao.getList(flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.createCriteria()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        return relationMapper.countByExample(example);
    }
}
