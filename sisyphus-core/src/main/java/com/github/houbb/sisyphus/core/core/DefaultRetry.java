package com.github.houbb.sisyphus.core.core;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.core.Retry;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultAttemptTime;
import com.github.houbb.sisyphus.core.model.DefaultRetryAttempt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 默认的重试实现
 *
 * @author binbin.hou
 * @since 0.0.1
 * @param <R> 泛型
 */
@ThreadSafe
public class DefaultRetry<R> implements Retry<R> {

    @Override
    public R retry(RetryContext<R> context) {
        List<RetryAttempt<R>> history = new ArrayList<>();

        //1. 执行方法
        int attempts = 1;
        final Callable<R> callable = context.callable();
        RetryAttempt<R> retryAttempt = execute(callable, attempts, history);

        //2. 是否进行重试
        //2.1 触发执行的 condition
        //2.2 不触发 stop 策略
        final RetryCondition retryCondition = context.condition();
        final RetryStop retryStop = context.stop();
        final RetryBlock retryBlock = context.block();
        final RetryWait retryWait = context.waits();
        final RetryListen retryListen = context.listen();

        while (retryCondition.condition(retryAttempt)
                && !retryStop.stop(retryAttempt)) {
            // 线程阻塞
            WaitTime waitTime = retryWait.waitTime(retryAttempt);
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

        // 返回最后一次尝试的结果
        return retryAttempt.result();
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
            throwable = e;
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
