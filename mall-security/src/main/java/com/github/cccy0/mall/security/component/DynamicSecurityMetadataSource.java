package com.github.cccy0.mall.security.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 动态权限数据源, 用来获取动态权限规则
 * @author Zhai
 * 2020/9/23 22:57
 */
@Configuration
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Map<String, ConfigAttribute> configAttributeMap;
    private final DynamicSecurityService dynamicSecurityService;

    public DynamicSecurityMetadataSource(DynamicSecurityService dynamicSecurityService) {
        this.dynamicSecurityService = dynamicSecurityService;
    }

    /**
     * 加载 url:权限标识 Map
     */
    @PostConstruct
    private void init() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        if (configAttributeMap != null) {
            configAttributeMap.clear();
        }
    }

    /**
     *
     * @param object FilterInvocation: 这个类的作用本身很简单，就是把doFilter传进来的request,response和FilterChain对象保存起来，供FilterSecurityInterceptor的处理代码调用。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (configAttributeMap == null) {
            init();
        }
        // 获取当前访问路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取访问url所需permission
        return configAttributeMap.entrySet()
                .stream()
                .filter((entry) -> antPathMatcher.match(entry.getKey(), path))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 是否支持 DynamicSecurityFilter.getSecureObjectClass(), 也就是此类中getAttributes(Object object)传进来的参数
     * @param clazz FilterInvocation.class
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // 判断FilterInvocation.class是否为clazz或clazz的父类或接口
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
