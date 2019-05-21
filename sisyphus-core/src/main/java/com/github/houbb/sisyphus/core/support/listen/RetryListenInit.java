package com.github.houbb.sisyphus.core.support.listen;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;

import java.util.List;

/**
 * 监听器初始化
 * @author binbin.hou
 * @since 0.0.1
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
