package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

/**
 * 重试条件工具类
 * 1. 结果 是否存在 是否等于
 * 2. 异常 是否存在异常
 * 3. 异常的类型。
 *
 * 待优化点：所有的 condition 实现可以写成单例实现。
 * @author binbin.hou
 * @since 0.0.1
 */
public final class RetryConditions {

    private RetryConditions(){}

    /**
     * 结果为空
     * @param <R> 单例
     * @return 结果为空
     */
    public static <R> RetryCondition<R> isNullResult() {
        return new AbstractResultRetryCondition<R>() {
            @Override
            protected boolean resultCondition(R result) {
                return ObjectUtil.isNull(result);
            }
        };
    }

    /**
     * 结果不为空
     * @param <R> 单例
     * @return 结果为空
     */
    public static <R> RetryCondition<R> isNotNullResult() {
        return new AbstractResultRetryCondition<R>() {
            @Override
            protected boolean resultCondition(R result) {
                return ObjectUtil.isNotNull(result);
            }
        };
    }

    /**
     * 结果等于预期值
     * @param excepted 预期值
     * @param <R> 单例
     * @return 结果为空
     */
    public static <R> RetryCondition<R> isEqualsResult(final R excepted) {
        return new AbstractResultRetryCondition<R>() {
            @Override
            protected boolean resultCondition(R result) {
                if(ObjectUtil.isNull(result)) {
                    return false;
                }
                return result.equals(excepted);
            }
        };
    }

    /**
     * 结果不等于预期值
     * @param excepted 预期值
     * @param <R> 单例
     * @return 结果为空
     */
    public static <R> RetryCondition<R> isNotEqualsResult(final R excepted) {
        return new AbstractResultRetryCondition<R>() {
            @Override
            protected boolean resultCondition(R result) {
                if(ObjectUtil.isNull(result)) {
                    return true;
                }
                return !result.equals(excepted);
            }
        };
    }

    /**
     * 程序执行过程中遇到异常
     * @return 重试条件
     * @since 0.0.2
     */
    public static RetryCondition hasExceptionCause() {
        return new AbstractCauseRetryCondition() {
            @Override
            protected boolean causeCondition(Throwable throwable) {
                return ObjectUtil.isNotNull(throwable);
            }
        };
    }

    /**
     * 是预期的异常类型
     * @param exClass 异常类型
     * @return 异常重试条件
     * @since 0.0.2
     */
    public static RetryCondition isExceptionCauseType(final Class<? extends Throwable> exClass) {
        return new AbstractCauseRetryCondition() {
            @Override
            protected boolean causeCondition(Throwable throwable) {
                return exClass.isAssignableFrom(throwable.getClass());
            }
        };
    }

    /**
     * 将多个条件整合在一起
     * @param retryConditions 重试条件
     * @return 一个重试条件
     * @since 0.0.2
     */
    public static RetryCondition conditions(final RetryCondition ... retryConditions) {
        if(ArrayUtil.isEmpty(retryConditions)) {
            return AlwaysFalseRetryCondition.getInstance();
        }

        return new AbstractRetryConditionInit() {
            @Override
            protected void init(Pipeline<RetryCondition> pipeline, RetryAttempt retryAttempt) {
                for(RetryCondition retryCondition : retryConditions) {
                    pipeline.addLast(retryCondition);
                }
            }
        };
    }

}
