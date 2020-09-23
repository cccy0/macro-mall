package com.github.cccy0.mall.security.component;

import com.github.cccy0.mall.security.config.IgnoreUrlsConfig;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 动态权限过滤器, 用来实现基于url的动态权限过滤 所以不需要@PreAuthrize那些注解
 * @author Zhai
 * 2020/9/23 23:27
 */

public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    private final DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
    private final IgnoreUrlsConfig ignoreUrlsConfig;

    public DynamicSecurityFilter(DynamicSecurityMetadataSource dynamicSecurityMetadataSource, IgnoreUrlsConfig ignoreUrlsConfig, DynamicAccessDecisionManager dynamicAccessDecisionManager) {
        this.dynamicSecurityMetadataSource = dynamicSecurityMetadataSource;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        // OPTIONS请求放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        // 白名单请求放行
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if (ignoreUrlsConfig.getUrls().stream().anyMatch((ignoreUrl) -> pathMatcher.match(ignoreUrl, fi.getRequestUrl()))) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        // 先调用调用DynamicSecurityMetadataSource.getAttributes(fi), 然后调用AccessDecisionManager中的decide方法进行鉴权
        InterceptorStatusToken token = beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return dynamicSecurityMetadataSource;
    }
}
