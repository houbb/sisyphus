package com.github.houbb.sisyphus.annotation.annotation;

import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.support.condition.ExceptionCauseRetryCondition;
import com.github.houbb.sisyphus.core.support.listen.NoRetryListen;
import com.github.houbb.sisyphus.core.support.recover.NoRecover;
import com.github.houbb.sisyphus.core.support.wait.NoRetryWait;

import java.lang.annotation.*;

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
public @interface Retry {

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
     * 设置等待策略
     * 1. 默认不进行任何等待
     * 2. 这里使用的对象必须是有无参构造函数的对象。
     * 3. 如果想使用原来的类，可以统一调整入参。waitContext
     * @return 等待策略
     */
    Class<? extends RetryWait> waits() default NoRetryWait.class;

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

}
