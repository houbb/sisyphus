package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

import java.util.concurrent.TimeUnit;

/**
 * 重试等待初始化类
 * 1. 用于一次初始化多个等待类
 * 2. 等待时间，是多个等待时间的和。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public abstract class RetryWaitInit implements RetryWait {

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        Pipeline<RetryWait> pipeline = new DefaultPipeline<>();
        this.init(pipeline, retryAttempt);

        long totalTimeMills = 0;
        for(RetryWait retryWait : pipeline.list()) {
            WaitTime waitTime = retryWait.waitTime(retryAttempt);
            totalTimeMills += waitTime.unit().convert(waitTime.time(), TimeUnit.MILLISECONDS);
        }
        return new DefaultWaitTime(totalTimeMills);
    }

    /**
     * 初始化列表
     * @param pipeline 当前列表泳道
     * @param retryAttempt 执行信息
     */
    protected abstract void init(final Pipeline<RetryWait> pipeline,
                                 final RetryAttempt retryAttempt);

}
