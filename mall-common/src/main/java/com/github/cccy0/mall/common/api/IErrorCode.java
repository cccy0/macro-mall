package com.github.cccy0.mall.common.api;

/**
 * @author Zhai
 * 2020/9/10 23:20
 */

public interface IErrorCode {
    /**
     * 获取错误码
     * @return 错误码
     */
    long getCode();

    /**
     * 获取错误消息
     * @return 错误消息
     */
    String getMessage();
}
