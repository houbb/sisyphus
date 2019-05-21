package com.github.houbb.sisyphus.core.context;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

import java.util.concurrent.Callable;

/**
 * 默认的重试上下文
 * @author binbin.hou
 * @since 1.0.0
 */
@NotThreadSafe
public class DefaultRetryContext<R>  implements RetryContext<R> {

    /**
     * 执行的条件
     */
    private RetryCondition condition;

    /**
     * 等待策略列表
     */
    private RetryWait waits;

    /**
     * 阻塞实现
     */
    private RetryBlock block;

    /**
     * 停止策略
     */
    private RetryStop stop;

    /**
     * 可执行的方法
     */
    private Callable<R> callable;

    /**
     * 监听器
     */
    private RetryListen listen;

    /**
     * 恢复策略
     */
    private Recover recover;

    @Override
    public RetryCondition condition() {
        return condition;
    }

    public DefaultRetryContext<R> condition(RetryCondition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public RetryWait waits() {
        return waits;
    }

    public DefaultRetryContext<R> waits(RetryWait waits) {
        this.waits = waits;
        return this;
    }

    @Override
    public RetryBlock block() {
        return block;
    }

    public DefaultRetryContext<R> block(RetryBlock block) {
        this.block = block;
        return this;
    }

    @Override
    public RetryStop stop() {
        return stop;
    }

    public DefaultRetryContext<R> stop(RetryStop stop) {
        this.stop = stop;
        return this;
    }

    @Override
    public Callable<R> callable() {
        return callable;
    }

    public DefaultRetryContext<R> callable(Callable<R> callable) {
        this.callable = callable;
        return this;
    }

    @Override
    public RetryListen listen() {
        return listen;
    }

    public DefaultRetryContext<R> listen(RetryListen listen) {
        this.listen = listen;
        return this;
    }

    @Override
    public Recover recover() {
        return recover;
    }

    public DefaultRetryContext<R> recover(Recover recover) {
        this.recover = recover;
        return this;
    }
}
