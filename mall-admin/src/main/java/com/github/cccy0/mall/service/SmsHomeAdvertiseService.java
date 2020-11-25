package com.github.cccy0.mall.service;

import com.github.cccy0.mall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页广告管理Service
 *
 * @author cy
 */
public interface SmsHomeAdvertiseService {
    /**
     * 添加广告
     *
     * @param advertise advertise
     * @return int
     */
    int create(SmsHomeAdvertise advertise);

    /**
     * 批量删除广告
     *
     * @param ids ids
     * @return int
     */
    int delete(List<Long> ids);

    /**
     * 修改上、下线状态
     *
     * @param id     id
     * @param status status
     * @return int
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取广告详情
     *
     * @param id id
     * @return SmsHomeAdvertise
     */
    SmsHomeAdvertise getItem(Long id);

    /**
     * 更新广告
     *
     * @param id        id
     * @param advertise advertise
     * @return int
     */
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * 分页查询广告
     *
     * @param name     name
     * @param type     type
     * @param endTime  endTime
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return List
     */
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}
