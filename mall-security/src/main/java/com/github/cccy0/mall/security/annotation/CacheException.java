package com.github.cccy0.mall.security.annotation;

import java.lang.annotation.*;

/**
 * 有该注解的方法会抛异常
 * @author Zhai
 * 2020/9/22 22:23
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
