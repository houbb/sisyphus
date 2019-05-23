package com.github.houbb.sisyphus.annotation.proxy;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.proxy.IMethodHandler;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.annotation.annotation.RetryWait;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.exception.RetryException;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.core.core.RetryWaiter;
import com.github.houbb.sisyphus.core.core.Retryer;
import com.github.houbb.sisyphus.core.support.wait.NoRetryWait;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 默认的重试方法实现
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class RetryMethodHandler implements IMethodHandler {

    @Override
    public Object handle(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 判断注解信息
        boolean retryable = method.isAnnotationPresent(Retry.class);
        if(!retryable) {
            return method.invoke(proxy, args);
        }

        //2. 包含注解才进行处理
        final Callable callable = buildCallable(proxy, method, args);
        Retry retry = method.getAnnotation(Retry.class);
        //3. 执行
        return this.retry(retry, callable);
    }

    /**
     * 执行重试
     * @param retry 重试注解
     * @param callable callable 方法
     * @return 执行结果
     */
    public Object retry(final Retry retry,
                      final Callable callable) {
        //3. 特殊处理下等待时间信息
        final RetryWaitContext[] contexts = ArrayUtil.listToArray(buildRetryWaitContext(retry));
        // 可以优化的点，让用户指定是否为线程安全。比如指定注解，则使用单例。
        // 这里可以对 getInstance() 进行优化封装。
        final RetryCondition retryCondition = InstanceFactory.getInstance().threadSafe(retry.condition());
        final RetryListen retryListen = InstanceFactory.getInstance().threadSafe(retry.listen());
        final Recover recover = InstanceFactory.getInstance().threadSafe(retry.recover());

        return Retryer.newInstance()
                .maxAttempt(retry.maxAttempt())
                .condition(retryCondition)
                .listen(retryListen)
                .recover(recover)
                .retryWaitContext(contexts)
                .retry(callable);
    }

    /**
     * 构建重试等待上下文
     * @param retry 重试信息
     * @return 上下文列表
     */
    private <R> List<RetryWaitContext<R>> buildRetryWaitContext(final Retry retry) {
        List<RetryWaitContext<R>> resultList = Collections.singletonList(RetryWaiter.<R>retryWait(NoRetryWait.class).retryWaitContext());
        if(ObjectUtil.isNull(retry)) {
            return resultList;
        }

        RetryWait[] retryWaits = retry.waits();
        if(ArrayUtil.isEmpty(retryWaits)) {
            return resultList;
        }

        resultList = new ArrayList<>();
        for(RetryWait retryWait : retryWaits) {
            RetryWaitContext<R> context = RetryWaiter
                    .<R>retryWait(retryWait.retryWait())
                    .min(retryWait.min())
                    .max(retryWait.max())
                    .factor(retryWait.factor())
                    .value(retryWait.value())
                    .retryWaitContext();
            resultList.add(context);
        }

        return resultList;
    }

    /**
     * 构建 callable
     * @param proxy 代理
     * @param method 方法
     * @param args 参数
     * @return callable 对象
     */
    private Callable buildCallable(final Object proxy, final Method method, final Object[] args) {
        return new Callable() {
            @Override
            public Object call() throws Exception {
                return method.invoke(proxy, args);
            }
        };
    }

}
