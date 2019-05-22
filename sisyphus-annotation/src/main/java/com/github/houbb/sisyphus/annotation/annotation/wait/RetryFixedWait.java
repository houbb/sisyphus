package com.github.houbb.sisyphus.annotation.annotation.wait;

import java.lang.annotation.*;

/**
 * 固定等待策略
 * @author binbin.hou
 * @since 0.0.3
 */
@RetryWait
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.METHOD)
public @interface RetryFixedWait {

    /**
     * 固定等待时间（毫秒）
     * 1. 默认等待时间为 1s
     * @return 最小等待时间（毫秒）
     */
    long value() default 1000;

}
