package com.github.cccy0.mall.security.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 动态权限决策管理器, 用户判断用户是否有访问权限 主要验证逻辑就是在这里
 * @author Zhai
 * 2020/9/22 22:40
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication the caller invoking the method (not null)
     * @param object the secured object being called
     * @param configAttributes the configuration attributes associated with the secured 比如@PreAuthorze()定义的或者在config配置类里定义的.access()等 这边是在DynamicSecurityMetadataSource里面获取到的
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 没有则放行
        if (CollUtil.isEmpty(configAttributes)) {
            return;
        }
        for (ConfigAttribute configAttribute : configAttributes) {
            String needAuthority = configAttribute.getAttribute().trim();
            // 将 用户的GrantedAuthorities与请求所需的Authority匹配, 如果有则放行
            if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(needAuthority::equals)) {
                return;
            }
        }
        throw new AccessDeniedException("无访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
