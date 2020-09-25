package com.github.cccy0.mall.config;

import com.github.cccy0.mall.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhai
 * 2020/9/25 21:37
 */
@Configuration
public class DynamicSecurityServiceImpl implements DynamicSecurityService {
    @Autowired
    private UmsResourceSer

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        ConcurrentHashMap<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        res
    }
}
