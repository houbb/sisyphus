package com.github.houbb.sisyphus.api.support.listen;

import com.github.houbb.sisyphus.api.model.RetryAttempt;

/**
 * 重试监听
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryListen {

    /**
     * 执行重试监听
     * @param attempt 重试
     * @param <R> 泛型
     */
    <R> void listen(final RetryAttempt<R> attempt);

}
