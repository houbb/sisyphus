package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.pipeline.Pipeline;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

/**
 * 重试等待初始化类
 * 1. 用于一次初始化多个等待类
 * 2. 等待时间，是多个等待时间的和。
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public abstract class RetryWaitInit implements RetryWait {

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        return null;
    }

    /**
     * 初始化列表
     * @param pipeline 当前列表泳道
     * @param retryAttempt 执行信息
     */
    protected abstract void init(final Pipeline<RetryCondition> pipeline,
                                 final RetryAttempt retryAttempt);
}
