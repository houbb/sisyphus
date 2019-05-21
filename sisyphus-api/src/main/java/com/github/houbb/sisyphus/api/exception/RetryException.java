package com.github.houbb.sisyphus.api.exception;

/**
 * 重试异常类
 * @author binbin.hou
 * @since 0.0.1
 */
public class RetryException extends RuntimeException {

    public RetryException() {
    }

    public RetryException(String message) {
        super(message);
    }

    public RetryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryException(Throwable cause) {
        super(cause);
    }

    public RetryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
