# sisyphus 注解

配置具有很高的灵活性，但是对于开发人员的使用，就没有注解那样简单灵活。

所以本框架也实现了基于注解的重试。

## 设计的规范

保证接口和注解二者的统一性。

## maven 引入

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>sisyphus-annotation</artifactId>
    <version>${project.version}</version>
</dependency>
```

# 注解

核心注解主要有两个。

## Retry

用于指定重试的相关配置。

```java
/**
 * 重试注解
 * 1. 实际需要，只允许放在方法上。
 * 2. 如果放在接口上，是否所有的子类都生效？为了简单明确，不提供这种实现。
 * 3. 保持注解和接口的一致性。{@link com.github.houbb.sisyphus.api.core.Retry} 接口
 * @author binbin.hou
 * @since 0.0.3
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RetryAble(DefaultRetryAbleHandler.class)
public @interface Retry {

    /**
     * 重试类实现
     * @return 重试
     * @since 0.0.5
     */
    Class<? extends com.github.houbb.sisyphus.api.core.Retry> retry() default DefaultRetry.class;

    /**
     * 最大尝试次数
     * 1. 包含方法第一次正常执行的次数
     * @return 次数
     */
    int maxAttempt() default 3;

    /**
     * 重试触发的场景
     * @return 重试触发的场景
     */
    Class<? extends RetryCondition> condition() default ExceptionCauseRetryCondition.class;

    /**
     * 监听器
     * 1. 默认不进行监听
     * @return 监听器
     */
    Class<? extends RetryListen> listen() default NoRetryListen.class;

    /**
     * 恢复操作
     * 1. 默认不进行任何恢复操作
     * @return 恢复操作对应的类
     */
    Class<? extends Recover> recover() default NoRecover.class;

    /**
     * 等待策略
     * 1. 支持指定多个，如果不指定，则不进行任何等待，
     * @return 等待策略
     */
    RetryWait[] waits() default {};

}
```


## RetryWait

用于指定重试的等待策略。

```java
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
```

# 注解的使用

定义好了注解，肯定要有注解的相关使用。

关于注解的使用，主要有两种方式。

## Proxy+CGLIB

基于代理模式和字节码增强。

如果是项目中没有使用 spring，直接使用这种方式比较方便。

## Spring-AOP

可以和 spring 直接整合。

使用方式和 spring-retry 是一样的。