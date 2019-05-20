package com.github.houbb.sisyphus.core.context;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 默认的重试上下文
 * @author binbin.hou
 * @since 1.0.0
 */
@NotThreadSafe
public class DefaultRetryContext<R> implements RetryContext<R> {

    /**
     * 执行的条件
     */
    private List<RetryCondition> conditions;

    /**
     * 等待策略列表
     */
    private List<RetryWait> waits;

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
     * 创建上下文实例
     * @return this
     */
    public static DefaultRetryContext newInstance() {
        return new DefaultRetryContext();
    }

    @Override
    public List<RetryCondition> conditions() {
        return conditions;
    }

    public DefaultRetryContext conditions(List<RetryCondition> conditions) {
        this.conditions = conditions;
        return this;
    }

    @Override
    public List<RetryWait> waits() {
        return waits;
    }

    public DefaultRetryContext waits(List<RetryWait> waits) {
        this.waits = waits;
        return this;
    }

    @Override
    public RetryBlock block() {
        return block;
    }

    public DefaultRetryContext block(RetryBlock block) {
        this.block = block;
        return this;
    }

    @Override
    public RetryStop stop() {
        return stop;
    }

    public DefaultRetryContext stop(RetryStop stop) {
        this.stop = stop;
        return this;
    }

    @Override
    public Callable<R> callable() {
        return callable;
    }

    public DefaultRetryContext callable(Callable<R> callable) {
        this.callable = callable;
        return this;
    }
}
