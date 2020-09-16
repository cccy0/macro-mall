package com.github.cccy0.mall.common.exception;

import com.github.cccy0.mall.common.api.IErrorCode;

/**
 * 自定义Api异常
 *
 * @author Zhai
 * 2020/9/16 23:15
 */

public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
