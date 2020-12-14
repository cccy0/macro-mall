package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.CmsPrefrenceAreaProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优选和商品关系操作Dao
 *
 * @author cy
 */
public interface CmsPrefrenceAreaProductRelationDao {
    /**
     * 批量创建
     *
     * @param prefrenceAreaProductRelationList prefrenceAreaProductRelationList
     * @return int
     */
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
