package com.github.houbb.sisyphus.core.core.retry;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.response.exception.ExceptionUtil;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.core.Retry;
import com.github.houbb.sisyphus.api.exception.RetryException;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.context.DefaultRetryWaitContext;
import com.github.houbb.sisyphus.core.model.DefaultAttemptTime;
import com.github.houbb.sisyphus.core.model.DefaultRetryAttempt;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 默认的重试实现
 *
 * @author binbin.hou
 * @since 0.0.1
 * @param <R> 泛型
 */
@ThreadSafe
public class DefaultRetry<R> implements Retry<R> {

    /**
     * 获取单例
     * @return 获取单例
     */
    public static DefaultRetry getInstance() {
        return InstanceFactory.getInstance().singleton(DefaultRetry.class);
    }

    @Override
    public R retryCall(RetryContext<R> context) {
        List<RetryAttempt<R>> history = new ArrayList<>();

        //1. 执行方法
        int attempts = 1;
        final Callable<R> callable = context.callable();
        RetryAttempt<R> retryAttempt = execute(callable, attempts, history);

        //2. 是否进行重试
        //2.1 触发执行的 condition
        //2.2 不触发 stop 策略
        final List<RetryWaitContext<R>> waitContextList = context.waitContext();
        final RetryCondition retryCondition = context.condition();
        final RetryStop retryStop = context.stop();
        final RetryBlock retryBlock = context.block();
        final RetryListen retryListen = context.listen();

        while (retryCondition.condition(retryAttempt)
                && !retryStop.stop(retryAttempt)) {
            // 线程阻塞
            WaitTime waitTime = calcWaitTime(waitContextList, retryAttempt);
            retryBlock.block(waitTime);

            // 每一次执行会更新 executeResult
            attempts++;
            history.add(retryAttempt);
            retryAttempt = this.execute(callable, attempts, history);

            // 触发 listener
            retryListen.listen(retryAttempt);
        }

        // 如果仍然满足触发条件。但是已经满足停止策略
        // 触发 recover
        if (retryCondition.condition(retryAttempt)
                && retryStop.stop(retryAttempt)) {
            final Recover recover = context.recover();
            recover.recover(retryAttempt);
        }

        // 如果最后一次有异常，直接抛出异常 v0.0.2
        final Throwable throwable = retryAttempt.cause();
        if(ObjectUtil.isNotNull(throwable)) {
            //1. 运行时异常，则直接抛出
            //2. 非运行时异常，则包装成为 RetryException
            if(throwable instanceof RuntimeException) {
                throw (RuntimeException)throwable;
            }
            throw new RetryException(retryAttempt.cause());
        }
        // 返回最后一次尝试的结果
        return retryAttempt.result();
    }

    /**
     * 构建等待时间
     * @param waitContextList 等待上下文列表
     * @param retryAttempt 重试信息
     * @return 等待时间毫秒
     */
    private WaitTime calcWaitTime(final List<RetryWaitContext<R>> waitContextList,
                              final RetryAttempt<R> retryAttempt) {
        long totalTimeMills = 0;
        for(RetryWaitContext context : waitContextList) {
            RetryWait retryWait = (RetryWait) InstanceFactory.getInstance().threadSafe(context.retryWait());
            final RetryWaitContext retryWaitContext = buildRetryWaitContext(context, retryAttempt);
            WaitTime waitTime = retryWait.waitTime(retryWaitContext);
            totalTimeMills += TimeUnit.MILLISECONDS.convert(waitTime.time(), waitTime.unit());
        }
        return new DefaultWaitTime(totalTimeMills);
    }

    /**
     * 构建重试等待上下文
     * @param waitContext 等待上下文
     * @param retryAttempt 重试信息
     * @return 构建后的等待信息
     */
    private RetryWaitContext buildRetryWaitContext(RetryWaitContext waitContext,
                                                   final RetryAttempt<R> retryAttempt) {
        DefaultRetryWaitContext<R> context = (DefaultRetryWaitContext<R>)waitContext;
        context.attempt(retryAttempt.attempt());
        context.result(retryAttempt.result());
        context.history(retryAttempt.history());
        context.cause(retryAttempt.cause());
        context.time(retryAttempt.time());
        return context;
    }

    /**
     * 执行 callable 方法
     *
     * @param callable 待执行的方法
     * @param attempts 重试次数
     * @param history  历史记录
     * @return 相关的额执行信息
     */
    private RetryAttempt<R> execute(final Callable<R> callable,
                                    final int attempts,
                                    final List<RetryAttempt<R>> history) {
        final Date startTime = DateUtil.now();

        DefaultRetryAttempt<R> retryAttempt = new DefaultRetryAttempt<>();
        Throwable throwable = null;
        R result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            throwable = ExceptionUtil.getActualThrowable(e);
        }
        final Date endTime = DateUtil.now();
        final long costTimeInMills = DateUtil.costTimeInMills(startTime, endTime);
        DefaultAttemptTime attemptTime = new DefaultAttemptTime();
        attemptTime.startTime(startTime)
                .endTime(endTime)
                .costTimeInMills(costTimeInMills);

        retryAttempt.attempt(attempts)
                .time(attemptTime)
                .cause(throwable)
                .result(result)
                .history(history);

        return retryAttempt;
    }

}
