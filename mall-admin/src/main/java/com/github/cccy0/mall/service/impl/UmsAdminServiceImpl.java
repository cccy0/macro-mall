package com.github.cccy0.mall.service.impl;

import com.github.cccy0.mall.dao.UmsAdminPermissionRelationDao;
import com.github.cccy0.mall.dao.UmsAdminRoleRelationDao;
import com.github.cccy0.mall.dto.UmsAdminParam;
import com.github.cccy0.mall.dto.UpdateAdminPasswordParam;
import com.github.cccy0.mall.mapper.UmsAdminLoginLogMapper;
import com.github.cccy0.mall.mapper.UmsAdminMapper;
import com.github.cccy0.mall.mapper.UmsAdminPermissionRelationMapper;
import com.github.cccy0.mall.mapper.UmsAdminRoleRelationMapper;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.model.UmsRole;
import com.github.cccy0.mall.security.util.JwtTokenUtil;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhai
 * 2020/9/25 22:06
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    private JwtTokenUtil jwtTokenUtil;
    private PasswordEncoder passwordEncoder;
    private UmsAdminMapper umsAdminMapper;
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    private UmsAdminPermissionRelationMapper umsAdminPermissionRelationMapper;
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    private UmsAdminPermissionRelationDao umsAdminPermissionRelationDao;
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;
    private UmsAdminCacheService umsAdminCacheService;

    // region 注入

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUmsAdminMapper(UmsAdminMapper umsAdminMapper) {
        this.umsAdminMapper = umsAdminMapper;
    }

    @Autowired
    public void setUmsAdminRoleRelationMapper(UmsAdminRoleRelationMapper umsAdminRoleRelationMapper) {
        this.umsAdminRoleRelationMapper = umsAdminRoleRelationMapper;
    }

    @Autowired
    public void setUmsAdminPermissionRelationMapper(UmsAdminPermissionRelationMapper umsAdminPermissionRelationMapper) {
        this.umsAdminPermissionRelationMapper = umsAdminPermissionRelationMapper;
    }

    @Autowired
    public void setUmsAdminRoleRelationDao(UmsAdminRoleRelationDao umsAdminRoleRelationDao) {
        this.umsAdminRoleRelationDao = umsAdminRoleRelationDao;
    }

    @Autowired
    public void setUmsAdminPermissionRelationDao(UmsAdminPermissionRelationDao umsAdminPermissionRelationDao) {
        this.umsAdminPermissionRelationDao = umsAdminPermissionRelationDao;
    }

    @Autowired
    public void setUmsAdminLoginLogMapper(UmsAdminLoginLogMapper umsAdminLoginLogMapper) {
        this.umsAdminLoginLogMapper = umsAdminLoginLogMapper;
    }

    @Autowired
    public void setUmsAdminCacheService(UmsAdminCacheService umsAdminCacheService) {
        this.umsAdminCacheService = umsAdminCacheService;
    }

    // endregion

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        // todo: admin管理员逻辑
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return null;
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int update(Long id, UmsAdmin umsAdmin) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        return 0;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return null;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
