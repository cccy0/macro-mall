package com.github.cccy0.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author Zhai
 * 2020/9/23 23:05
 */

public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应Map
     * @return url:url所需权限标识
     */
    Map<String, ConfigAttribute> loadDataSource();
}
