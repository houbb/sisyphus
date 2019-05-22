package com.github.houbb.sisyphus.annotation.annotation.wait;

import java.lang.annotation.*;

/**
 * 随机等待策略
 * @author binbin.hou
 * @since 0.0.3
 */
@RetryWait
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.METHOD)
public @interface RetryRandomWait {

    /**
     * 最小等待时间（毫秒）
     * 1. 默认为 1s
     * @return 最小等待时间（毫秒）
     */
    long min() default 1000;

    /**
     * 最大等待时间（毫秒）
     * 1. 默认为 30min
     * @return 最大等待时间（毫秒）
     */
    long max() default 30 * 60 * 1000;

}
