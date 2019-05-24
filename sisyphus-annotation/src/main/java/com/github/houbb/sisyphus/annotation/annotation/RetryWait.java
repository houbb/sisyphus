package com.github.houbb.sisyphus.annotation.annotation;

import com.github.houbb.sisyphus.annotation.annotation.metadata.RetryWaitAble;
import com.github.houbb.sisyphus.annotation.handler.impl.DefaultRetryWaitAbleHandler;
import com.github.houbb.sisyphus.core.constant.RetryWaitConst;
import com.github.houbb.sisyphus.core.support.wait.NoRetryWait;

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
@RetryWaitAble(DefaultRetryWaitAbleHandler.class)
public @interface RetryWait {

    /**
     * 默认值
     * 1. fixed 模式，则对应固定等待时间
     * 2. 递增
     * @return 默认值
     */
    long value() default RetryWaitConst.VALUE_MILLS;

    /**
     * 最小值
     * @return 最小值
     */
    long min() default RetryWaitConst.MIN_MILLS;

    /**
     * 最大值
     * @return 最大值
     */
    long max() default RetryWaitConst.MAX_MILLS;

    /**
     * 影响因数
     * 1. 递增重试，默认为 {@link RetryWaitConst#INCREASE_MILLS_FACTOR}
     * 2. 指数模式。默认为 {@link RetryWaitConst#MULTIPLY_FACTOR}
     * @return 影响因数
     */
    double factor() default Double.MIN_VALUE;

    /**
     * 指定重试的等待时间 class 信息
     * @return 重试等待时间 class
     */
    Class<? extends com.github.houbb.sisyphus.api.support.wait.RetryWait> retryWait() default NoRetryWait.class;

}
