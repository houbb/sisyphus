package com.github.houbb.sisyphus.annotation.annotation.metadata;

import com.github.houbb.heaven.annotation.Metadata;
import com.github.houbb.sisyphus.annotation.handler.RetryAbleHandler;

import java.lang.annotation.*;

/**
 * 可重试的注解
 * 1. 用于注解之上用于指定重试信息
 * @author binbin.hou
 * @since 0.0.5
 * @see com.github.houbb.sisyphus.api.context.RetryContext 重试上下文
 */
@Metadata
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Inherited
public @interface RetryAble {

    /**
     * 对应的注解处理器
     * @return class 信息
     */
    Class<? extends RetryAbleHandler> value();

}
