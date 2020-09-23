package com.github.cccy0.mall.security.config;

import cn.hutool.core.util.ArrayUtil;
import com.github.cccy0.mall.security.component.DynamicAccessDecisionManager;
import com.github.cccy0.mall.security.component.DynamicSecurityFilter;
import com.github.cccy0.mall.security.component.DynamicSecurityMetadataSource;
import com.github.cccy0.mall.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Zhai
 * 2020/9/24 0:09
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class))
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // todo 自定义相关未认证或无访问权限处理类
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        DynamicAccessDecisionManager dynamicAccessDecisionManager = new DynamicAccessDecisionManager();
        DynamicSecurityMetadataSource dynamicSecurityMetadataSource = new DynamicSecurityMetadataSource(dynamicSecurityService);
        return new DynamicSecurityFilter(dynamicSecurityMetadataSource, ignoreUrlsConfig, dynamicAccessDecisionManager);
    }
}
