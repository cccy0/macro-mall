package com.github.cccy0.mall.security.aspect;

import com.github.cccy0.mall.security.annotation.CacheException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis切面
 * @author Zhai
 * 2020/9/22 22:23
 */
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * com.github.cccy0.mall.portal.service.*CacheService.*(..)) || execution(public * com.github.cccy0.mall.service.*CacheService.*(..))")
    public void cacheAspect() {}

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                logger.error(throwable.getMessage());
            }
        }
        return result;
    }
}
