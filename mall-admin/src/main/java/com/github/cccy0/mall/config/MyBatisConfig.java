package com.github.cccy0.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhai
 * 2020/9/17 21:55
 */
@MapperScan(basePackages = {"com.github.cccy0.mall.mapper", "com.github.cccy0.mall.dao"})
@Configuration
public class MyBatisConfig {
}
