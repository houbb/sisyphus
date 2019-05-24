package com.github.houbb.sisyphus.annotation.handler;

import com.github.houbb.sisyphus.api.context.RetryContext;

import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

/**
 * @author binbin.hou
 * @since 0.0.5
 * @param <A> 可重试注解
 * @param <T> 对象类型
 */
public interface RetryAbleHandler<A extends Annotation, T> {

    /**
     * 根据注解信息构建上下文
     * @param annotation 可重试注解
     * @param callable 待重试方法
     * @return 重试上下文
     */
    RetryContext<T> build(final A annotation,
                          final Callable<T> callable);

}
