package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级管理Service
 * @author cy
 */
public interface UmsMemberLevelService {
    /**
     * 获取所有会员等级
     * @param defaultStatus 是否为默认会员
     * @return List
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
