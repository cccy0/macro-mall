package com.github.cccy0.mall.common.api;

/**
 * @author Zhai
 * 2020/9/10 23:21
 */

public enum ResultCode implements IErrorCode{
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAILED(500, "操作失败"),
    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 未认证或Token过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 无权限
     */
    FORBIDDEN(403, "没有相关权限");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
