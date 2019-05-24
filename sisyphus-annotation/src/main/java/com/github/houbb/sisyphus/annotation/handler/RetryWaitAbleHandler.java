package com.github.houbb.sisyphus.annotation.handler;

import com.github.houbb.sisyphus.api.context.RetryWaitContext;

import java.lang.annotation.Annotation;

/**
 * 重试等待处理类
 * @author binbin.hou
 * @since 0.0.5
 * @param <A> 注解
 * @param <T> 对象类型
 */
public interface RetryWaitAbleHandler<A extends Annotation, T> {

    /**
     * 根据注解信息构建上下文
     * @param annotation 可重试等待注解
     * @return 重试等待上下文
     */
    RetryWaitContext<T> build(final A annotation);

}
