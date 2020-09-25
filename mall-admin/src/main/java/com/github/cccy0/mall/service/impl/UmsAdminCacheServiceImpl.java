package com.github.cccy0.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.cccy0.mall.common.service.RedisService;
import com.github.cccy0.mall.dao.UmsAdminRoleRelationDao;
import com.github.cccy0.mall.mapper.UmsAdminRoleRelationMapper;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsAdminRoleRelation;
import com.github.cccy0.mall.model.UmsAdminRoleRelationExample;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhai
 * 2020/9/25 21:54
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    private UmsAdminService umsAdminService;
    private RedisService redisService;
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Autowired
    public void setUmsAdminService(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setUmsAdminRoleRelationMapper(UmsAdminRoleRelationMapper umsAdminRoleRelationMapper) {
        this.umsAdminRoleRelationMapper = umsAdminRoleRelationMapper;
    }

    @Autowired
    public void setUmsAdminRoleRelationDao(UmsAdminRoleRelationDao umsAdminRoleRelationDao) {
        this.umsAdminRoleRelationDao = umsAdminRoleRelationDao;
    }

    @Value("${settings.redis.database}")
    private String redisDatabase;

    @Value("${settings.redis.expire.common}")
    private Long redisExpire;

    @Value("${settings.redis.key.admin}")
    private String redisKeyAdmin;

    @Value("${settings.redis.key.resourceList}")
    private String redisKeyResourceList;

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = umsAdminService.getItem(adminId);
        if (admin != null) {
            String key = redisDatabase + ":" + redisKeyAdmin + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        delResourceListByExample(example);
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        delResourceListByExample(example);
    }

    private void delResourceListByExample(UmsAdminRoleRelationExample example) {
        List<UmsAdminRoleRelation> list = umsAdminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            String keyPrefix = redisDatabase + ":" + redisKeyResourceList + ":";
            List<String> keys = list.stream().map(item -> keyPrefix + item.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = umsAdminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = redisDatabase + ":" + redisKeyAdmin + ":";
            List<String> keys = adminIdList.stream().map((adminId) -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = redisDatabase + ":" + redisKeyAdmin + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = redisDatabase + ":" + redisKeyAdmin + ":" + admin.getUsername();
        redisService.set(key, admin, redisExpire);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + adminId;
        redisService.set(key, resourceList, redisExpire);
    }
}
