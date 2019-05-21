package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

/**
 * 重试条件工具类
 * 1. 结果 是否存在 是否等于
 * 2. 异常 是否存在异常
 * 3. 异常的类型。
 * @author binbin.hou
 * @since 1.0.0
 */
public final class RetryConditions {

    private RetryConditions(){}

    /**
     * 结果为空
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

}
