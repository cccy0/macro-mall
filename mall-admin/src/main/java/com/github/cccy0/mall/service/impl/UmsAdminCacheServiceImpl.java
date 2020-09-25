package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.common.service.RedisService;
import com.github.cccy0.mall.dao.UmsAdminRoleRelationDao;
import com.github.cccy0.mall.mapper.UmsAdminRoleRelationMapper;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author Zhai
 * 2020/9/25 21:54
 */

public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    private UmsAdminService umsAdminService;
    private RedisService redisService;
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Value("${settings.redis.database}")
    private String redisDatabase;

    @Value("${settings.redis.expire.common}")
    private String redisExpire;

    @Value("${settings.redis.key.admin}")
    private String redisKeyAdmin;

    @Value("${settings.redis.key.resourceList}")
    private String redisKeyResourceList;

    @Override
    public void delAdmin(Long adminId) {
        // todo: 缓存用户信息相关实现
    }

    @Override
    public void delResourceList(Long adminId) {

    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        return null;
    }

    @Override
    public void setAdmin(UmsAdmin admin) {

    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
