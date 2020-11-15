package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.mapper.UmsMemberLevelMapper;
import com.github.cccy0.mall.model.UmsMemberLevel;
import com.github.cccy0.mall.model.UmsMemberLevelExample;
import com.github.cccy0.mall.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 * @author cy
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    private UmsMemberLevelMapper memberLevelMapper;

    @Autowired
    public void setMemberLevelMapper(UmsMemberLevelMapper memberLevelMapper) {
        this.memberLevelMapper = memberLevelMapper;
    }

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
