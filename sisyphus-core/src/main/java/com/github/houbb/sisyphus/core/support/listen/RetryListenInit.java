package com.github.houbb.sisyphus.core.support.listen;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.pipeline.Pipeline;
import com.github.houbb.sisyphus.core.support.pipeline.DefaultPipeline;

import java.util.List;

/**
 * 监听器初始化
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public abstract class RetryListenInit implements RetryListen {

    @Override
    public <R> void listen(RetryAttempt<R> attempt) {
        Pipeline<RetryListen> pipeline = new DefaultPipeline<>();
        this.init(pipeline, attempt);

        //执行
        final List<RetryListen> retryListenList = pipeline.list();
        for(RetryListen retryListen : retryListenList) {
            retryListen.listen(attempt);
        }
    }

    /**
     * 初始化监听器列表
     * @param pipeline 泳道
     * @param attempt 重试信息
     */
    protected abstract void init(final Pipeline<RetryListen> pipeline,
                                 final RetryAttempt attempt);

}
