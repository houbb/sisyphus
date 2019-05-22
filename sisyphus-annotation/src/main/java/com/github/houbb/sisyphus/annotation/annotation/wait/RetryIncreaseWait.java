package com.github.houbb.sisyphus.annotation.annotation.wait;

import java.lang.annotation.*;

/**
 * 递增等待策略
 * @author binbin.hou
 * @since 0.0.3
 */
@RetryWait
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.METHOD)
public @interface RetryIncreaseWait {

    /**
     * 初始化等待时间（毫秒）
     * @return 初始化等待时间（毫秒）
     */
    long init() default 1000;

    /**
     * 增加时间（毫秒）
     * @return 增加时间（毫秒）
     */
    long increase() default 1000;

}
