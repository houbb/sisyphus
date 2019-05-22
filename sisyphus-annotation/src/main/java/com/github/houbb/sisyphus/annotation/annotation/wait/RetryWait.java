package com.github.houbb.sisyphus.annotation.annotation.wait;

import java.lang.annotation.*;

/**
 * 重试等待策略
 * 1. 为了对应重试策略，所有的内置注解应该实现当前的注解。
 * 2. 是否允许自定义注解？
 *
 * 当注解+对象同时出现的时候，视为组合。
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
public @interface RetryWait {
}
