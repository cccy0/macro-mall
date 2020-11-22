package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.mapper.SmsHomeNewProductMapper;
import com.github.cccy0.mall.model.SmsHomeNewProduct;
import com.github.cccy0.mall.model.SmsHomeNewProductExample;
import com.github.cccy0.mall.service.SmsHomeNewProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 *
 * @author cy
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Autowired
    public void setHomeNewProductMapper(SmsHomeNewProductMapper homeNewProductMapper) {
        this.homeNewProductMapper = homeNewProductMapper;
    }

    @Override
    public int create(List<SmsHomeNewProduct> homeNewProducts) {
        for (SmsHomeNewProduct smsHomeNewProduct : homeNewProducts) {
            smsHomeNewProduct.setRecommendStatus(1);
            smsHomeNewProduct.setSort(0);
            homeNewProductMapper.insert(smsHomeNewProduct);
        }
        return homeNewProducts.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        return homeNewProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        return homeNewProductMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName)) {
            criteria.andProductNameLike("%" + productName + "%");
        }
        if (recommendStatus != null) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeNewProductMapper.selectByExample(example);
    }
}
