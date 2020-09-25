package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.UmsAdminParam;
import com.github.cccy0.mall.dto.UpdateAdminPasswordParam;
import com.github.cccy0.mall.model.UmsAdmin;
import com.github.cccy0.mall.model.UmsPermission;
import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhai
 * 2020/9/25 21:55
 */

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     *
     * @param username username
     * @return data
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     *
     * @param umsAdminParam 注册所需参数
     * @return 注册完成的用户信息
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     * @return 新的JWT
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     *
     * @param id id
     * @return data
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword  keyword
     * @param pageSize pageSize
     * @param pageNum  pageNum
     * @return data
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     *
     * @param id       id
     * @param umsAdmin umsAdmin
     * @return 影响行数
     */
    int update(Long id, UmsAdmin umsAdmin);

    /**
     * 删除指定用户
     *
     * @param id id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     *
     * @param adminId id
     * @param roleIds roleIds
     * @return 影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户的角色列表
     *
     * @param adminId adminId
     * @return 角色列表
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     *
     * @param adminId adminId
     * @return 资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改用户的+-权限
     *
     * @param adminId       adminId
     * @param permissionIds permissionIds
     * @return 影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param adminId adminId
     * @return 权限列表
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 修改密码
     *
     * @param updatePasswordParam updatePasswordParam
     * @return 影响行数
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     *
     * @param username username
     * @return UserDetails
     */
    UserDetails loadUserByUsername(String username);
}
