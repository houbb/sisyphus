package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

import java.util.concurrent.TimeUnit;

/**
 * 重试等待初始化类
 * 1. 用于一次初始化多个等待类
 * 2. 等待时间，是多个等待时间的和。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public abstract class AbstractRetryWaitInit extends AbstractRetryWait {

    @Override
    public WaitTime waitTime(RetryWaitContext retryWaitContext) {
        Pipeline<RetryWait> pipeline = new DefaultPipeline<>();
        this.init(pipeline, retryWaitContext);

        long totalTimeMills = 0;
        for(RetryWait retryWait : pipeline.list()) {
            WaitTime waitTime = retryWait.waitTime(retryWaitContext);
            totalTimeMills += waitTime.unit().convert(waitTime.time(), TimeUnit.MILLISECONDS);
        }

        return super.rangeCorrect(totalTimeMills, retryWaitContext.min(), retryWaitContext.max());
    }

    /**
     * 初始化列表
     * @param pipeline 当前列表泳道
     * @param retryWaitContext 执行上下文
     */
    protected abstract void init(final Pipeline<RetryWait> pipeline,
                                 final RetryWaitContext retryWaitContext);

}
