package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品参数，商品自定义规格属性Dao
 *
 * @author cy
 */
public interface PmsProductAttributeValueDao {
    /**
     * 批量创建
     *
     * @param productAttributeValueList productAttributeValueList
     * @return int
     */
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}
