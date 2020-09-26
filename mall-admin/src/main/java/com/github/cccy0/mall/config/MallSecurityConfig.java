package com.github.cccy0.mall.config;

import com.github.cccy0.mall.security.component.DynamicSecurityService;
import com.github.cccy0.mall.security.component.JwtAuthenticationTokenFilter;
import com.github.cccy0.mall.security.config.IgnoreUrlsConfig;
import com.github.cccy0.mall.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Zhai
 * 2020/9/25 21:34
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Override
    @Autowired
    public void setIgnoreUrlsConfig(IgnoreUrlsConfig ignoreUrlsConfig) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    @Override
    @Autowired
    public void setJwtAuthenticationTokenFilter(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Override
    @Autowired
    public void setDynamicSecurityService(DynamicSecurityService dynamicSecurityService) {
        this.dynamicSecurityService = dynamicSecurityService;
    }
}
