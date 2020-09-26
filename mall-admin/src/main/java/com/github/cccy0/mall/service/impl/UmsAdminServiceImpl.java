package com.github.cccy0.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.cccy0.mall.bo.AdminUserDetails;
import com.github.cccy0.mall.common.exception.Asserts;
import com.github.cccy0.mall.dao.UmsAdminPermissionRelationDao;
import com.github.cccy0.mall.dao.UmsAdminRoleRelationDao;
import com.github.cccy0.mall.dto.UmsAdminParam;
import com.github.cccy0.mall.dto.UpdateAdminPasswordParam;
import com.github.cccy0.mall.mapper.UmsAdminLoginLogMapper;
import com.github.cccy0.mall.mapper.UmsAdminMapper;
import com.github.cccy0.mall.mapper.UmsAdminPermissionRelationMapper;
import com.github.cccy0.mall.mapper.UmsAdminRoleRelationMapper;
import com.github.cccy0.mall.model.*;
import com.github.cccy0.mall.security.util.JwtTokenUtil;
import com.github.cccy0.mall.service.UmsAdminCacheService;
import com.github.cccy0.mall.service.UmsAdminService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        UmsAdmin admin = umsAdminCacheService.getAdmin(username);
        if (admin != null) {
            return admin;
        }
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(adminList)) {
            admin = adminList.get(0);
            umsAdminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtil.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        // 查询同名
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(adminList)) {
            return null;
        }
        // 二次加密
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码尽量md5等加密之后再传
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            logger.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return umsAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        if (StrUtil.isNotEmpty(keyword)) {
            example.createCriteria().andUsernameLike("%" + keyword + "%");
            example.or().andNickNameLike("%" + keyword + "%");
        }
        return umsAdminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UmsAdmin resources) {
        resources.setId(id);
        UmsAdmin umsAdmin = umsAdminMapper.selectByPrimaryKey(id);
        if (passwordEncoder.matches(resources.getPassword(), umsAdmin.getPassword())) {
            // 密码相同不需要修改
            resources.setPassword(null);
        } else {
            if (StrUtil.isBlank(resources.getPassword())) {
                resources.setPassword(null);
            } else {
                // 设置encode之后的password
                resources.setPassword(passwordEncoder.encode(resources.getPassword()));
            }
        }
        return umsAdminMapper.updateByPrimaryKeySelective(resources);
    }

    @Override
    public int delete(Long id) {
        int count = umsAdminMapper.deleteByPrimaryKey(id);
        umsAdminCacheService.delAdmin(id);
        umsAdminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        // 删除旧关系
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        umsAdminRoleRelationMapper.deleteByExample(example);
        // 建立新关系
        if (CollUtil.isNotEmpty(roleIds)) {
            List<UmsAdminRoleRelation> relationList = roleIds.stream().map((roleId) -> {
                UmsAdminRoleRelation adminRoleRelation = new UmsAdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                return adminRoleRelation;
            }).collect(Collectors.toList());
            umsAdminRoleRelationDao.insertList(relationList);
        }
        umsAdminCacheService.delResourceList(adminId);
        return roleIds == null ? 0 : roleIds.size();
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsAdminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = umsAdminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = umsAdminRoleRelationDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            umsAdminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        // 删除原+-权限关系
        UmsAdminPermissionRelationExample example = new UmsAdminPermissionRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        umsAdminPermissionRelationMapper.deleteByExample(example);
        // 获取所有角色权限 用于比对使用
        List<Long> rolePermissionIds = umsAdminRoleRelationDao.getRolePermissionList(adminId).stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(permissionIds)) {
            List<UmsAdminPermissionRelation> relationList = permissionIds.stream().map((permissionId) -> {
                UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
                relation.setAdminId(adminId);
                relation.setPermissionId(permissionId);
                // 如果角色权限中有这个权限, 则需要减去这个权限
                if (rolePermissionIds.contains(permissionId)) {
                    relation.setType(-1);
                } else {
                    // 否则就需要额外添加没有的权限
                    relation.setType(1);
                }
                return relation;
            }).collect(Collectors.toList());
            return umsAdminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isBlank(param.getUsername()) ||
                StrUtil.isBlank(param.getOldPassword()) ||
                StrUtil.isBlank(param.getNewPassword())) {
            return -1;
        }
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<UmsAdmin> admins = umsAdminMapper.selectByExample(example);
        if (CollUtil.isEmpty(admins)) {
            return -2;
        }
        UmsAdmin umsAdmin = admins.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        UmsAdmin newAdminInfo = new UmsAdmin();
        newAdminInfo.setId(umsAdmin.getId());
        newAdminInfo.setPassword(passwordEncoder.encode(param.getNewPassword()));
        umsAdminMapper.updateByPrimaryKeySelective(newAdminInfo);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取用户信息
        UmsAdmin umsAdmin = getAdminByUsername(username);
        if (umsAdmin != null) {
            List<UmsResource> resources = getResourceList(umsAdmin.getId());
            return new AdminUserDetails(umsAdmin, resources);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        if (umsAdmin == null) {
            return;
        }
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            loginLog.setIp(attributes.getRequest().getRemoteAddr());
        }
        umsAdminLoginLogMapper.insert(loginLog);
    }

    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        umsAdminMapper.updateByExampleSelective(record, example);
    }
}
