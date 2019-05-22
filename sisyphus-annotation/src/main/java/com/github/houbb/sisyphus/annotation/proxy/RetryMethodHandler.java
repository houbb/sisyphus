package com.github.houbb.sisyphus.annotation.proxy;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.proxy.IMethodHandler;
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.core.Retryer;

import java.lang.reflect.Method;
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
        Retry retry = method.getAnnotation(Retry.class);

        //3. 特殊处理下等待时间信息

        //4. 统一使用方法式编程调用
        final Callable callable = buildCallable(proxy, method, args);
        // 可以优化的点，让用户指定是否为线程安全。比如指定注解，则使用单例。
        // 这里可以对 getInstance() 进行优化封装。
        final RetryCondition retryCondition = InstanceFactory.getInstance().threadSafe(retry.condtion());
        final RetryListen retryListen = InstanceFactory.getInstance().threadSafe(retry.listen());
        final RetryWait retryWait = InstanceFactory.getInstance().threadSafe(retry.waits());
        final Recover recover = InstanceFactory.getInstance().threadSafe(retry.recover());

        return Retryer.newInstance()
                .maxAttempt(retry.maxAttempt())
                .condition(retryCondition)
                .listen(retryListen)
                .recover(recover)
                .waits(retryWait)
                .retry(callable);
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
