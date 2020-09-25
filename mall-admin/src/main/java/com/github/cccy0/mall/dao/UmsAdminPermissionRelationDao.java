package com.github.cccy0.mall.dao;

import com.github.cccy0.mall.model.UmsAdminPermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义用户权限关系管理Dao
 * @author Zhai
 * 2020/9/25 22:52
 */

public interface UmsAdminPermissionRelationDao {
    /**
     * 批量创建
     * @param list list
     * @return 影响行数
     */
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
