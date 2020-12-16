package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品审核日志管理Dao
 *
 * @author cy
 */
public interface PmsProductVertifyRecordDao {
    /**
     * 批量创建
     *
     * @param list list
     * @return int
     */
    int insertList(@Param("list") List<PmsProductVertifyRecord> list);
}
