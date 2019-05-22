package com.github.houbb.sisyphus.annotation.annotation.wait;

import java.lang.annotation.*;

/**
 * 指数等待策略注解
 * @author binbin.hou
 * @since 0.0.3
 */
@RetryWait
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.METHOD)
public @interface RetryExponentialWait {

    /**
     * 默认初始化时间
     * @return 初始化时间
     */
    long init() default 1000;

    /**
     * 默认的乘数
     * 1. 使用黄金分割作为默认比例
     * @return 比例
     */
    double multiplier() default 1.618;

    /**
     * 最大的等待时间
     * 1. 默认为 30min
     * @return 等待时间
     */
    long max() default 30 * 60 * 1000;

}
