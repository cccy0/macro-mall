package com.github.cccy0.mall.config;

import com.github.cccy0.mall.common.config.BaseSwaggerConfig;
import com.github.cccy0.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger相关配置
 *
 * @author Zhai
 * @date 2019/4/8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        SwaggerProperties properties = new SwaggerProperties();
        properties.setApiBasePackage("com.github.cccy0.mall.controller");
        properties.setTitle("mall-后台系统");
        properties.setDescription("mall后台相关接口文档");
        properties.setContactName("cccy0");
        properties.setVersion("1.0");
        properties.setEnableSecurity(true);
        return properties;
    }

}
