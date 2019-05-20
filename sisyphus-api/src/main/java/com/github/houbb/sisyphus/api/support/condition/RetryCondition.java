package com.github.houbb.sisyphus.api.support.condition;

import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;

/**
 * 重试的条件
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryCondition {

    /**
     * 是否满足重试的条件
     * @param retryAttempt 重试相关信息
     * @return 是否
     */
    boolean condition(final RetryAttempt retryAttempt);

}
