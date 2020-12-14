package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品和专题关系操作Dao
 *
 * @author cy
 */
public interface CmsSubjectProductRelationDao {
    /**
     * 批量创建
     *
     * @param subjectProductRelationList subjectProductRelationList
     * @return int
     */
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
