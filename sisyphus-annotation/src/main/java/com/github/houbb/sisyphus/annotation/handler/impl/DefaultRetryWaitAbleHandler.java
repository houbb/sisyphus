package com.github.houbb.sisyphus.annotation.handler.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.annotation.annotation.RetryWait;
import com.github.houbb.sisyphus.annotation.handler.RetryWaitAbleHandler;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.core.core.RetryWaiter;

/**
 * 默认的重试等待处理器
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class DefaultRetryWaitAbleHandler<R> implements RetryWaitAbleHandler<RetryWait, R> {

    @Override
    public RetryWaitContext<R> build(RetryWait retryWait) {
        return RetryWaiter
                .<R>retryWait(retryWait.retryWait())
                .min(retryWait.min())
                .max(retryWait.max())
                .factor(retryWait.factor())
                .value(retryWait.value())
                .context();
    }

}
