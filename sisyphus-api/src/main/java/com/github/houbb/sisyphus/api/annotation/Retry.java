package com.github.houbb.sisyphus.api.annotation;

import java.lang.annotation.*;

/**
 * 重试注解
 * @author binbin.hou
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Retry {

    /**
     * 最多重试次数
     * 1. 包含第一次
     * @return 3
     */
    int times() default 3;

}
