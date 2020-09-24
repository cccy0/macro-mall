package com.github.cccy0.mall.security.config;

import cn.hutool.core.util.ArrayUtil;
import com.github.cccy0.mall.security.component.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Zhai
 * 2020/9/24 0:09
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final DynamicSecurityService dynamicSecurityService;

    public SecurityConfig(IgnoreUrlsConfig ignoreUrlsConfig, JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, DynamicSecurityService dynamicSecurityService) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.dynamicSecurityService = dynamicSecurityService;
    }

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
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestfulAccessDeniedHandler())
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        if (dynamicSecurityService != null) {
            httpSecurity.addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DynamicSecurityFilter dynamicSecurityFilter() {
        DynamicAccessDecisionManager dynamicAccessDecisionManager = new DynamicAccessDecisionManager();
        DynamicSecurityMetadataSource dynamicSecurityMetadataSource = new DynamicSecurityMetadataSource(dynamicSecurityService);
        return new DynamicSecurityFilter(dynamicSecurityMetadataSource, ignoreUrlsConfig, dynamicAccessDecisionManager);
    }
}
