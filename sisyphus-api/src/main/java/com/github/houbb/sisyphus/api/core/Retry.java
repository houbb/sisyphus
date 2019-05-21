package com.github.houbb.sisyphus.api.core;

import com.github.houbb.sisyphus.api.context.RetryContext;

/**
 * @author binbin.hou
 * @since 1.0.0
 * @param <R> 泛型模板
 */
public interface Retry<R> {

    /**
     * 执行重试
     * @param context 执行上下文
     * @return 执行结果
     */
    R retry(final RetryContext<R> context);

}
