package com.github.houbb.sisyphus.annotation.handler.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.Instance;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.annotation.annotation.RetryWait;
import com.github.houbb.sisyphus.annotation.handler.RetryAbleHandler;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.core.core.RetryWaiter;
import com.github.houbb.sisyphus.core.core.Retryer;
import com.github.houbb.sisyphus.core.support.wait.NoRetryWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 默认的重试处理器
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class DefaultRetryAbleHandler<R> implements RetryAbleHandler<Retry, R> {

    @Override
    public RetryContext<R> build(final Retry annotation,
                                 final Callable<R> callable) {
        final Instance instance = InstanceFactory.getInstance();
        return Retryer.<R>newInstance()
                .callable(callable)
                .retry(instance.threadSafe(annotation.retry()))
                .condition(instance.threadSafe(annotation.condition()))
                .maxAttempt(annotation.maxAttempt())
                .recover(instance.threadSafe(annotation.recover()))
                .listen(instance.threadSafe(annotation.listen()))
                .retryWaitContext(ArrayUtil.listToArray(buildRetryWaitContext(annotation)))
                .context();
    }

    /**
     * 构建重试等待上下文
     * @param retry 重试信息
     * @return 上下文列表
     */
    private List<RetryWaitContext<R>> buildRetryWaitContext(final Retry retry) {
        List<RetryWaitContext<R>> resultList = Collections.singletonList(RetryWaiter.<R>retryWait(NoRetryWait.class).context());
        if(ObjectUtil.isNull(retry)) {
            return resultList;
        }

        RetryWait[] retryWaits = retry.waits();
        if(ArrayUtil.isEmpty(retryWaits)) {
            return resultList;
        }

        resultList = new ArrayList<>();
        final DefaultRetryWaitAbleHandler<R> waitAbleHandler = InstanceFactory
                .getInstance()
                .threadSafe(DefaultRetryWaitAbleHandler.class);
        for(RetryWait retryWait : retryWaits) {
            RetryWaitContext<R> context = waitAbleHandler.build(retryWait);
            resultList.add(context);
        }

        return resultList;
    }

}
