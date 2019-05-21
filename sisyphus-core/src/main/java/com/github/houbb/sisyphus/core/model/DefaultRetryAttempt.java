package com.github.houbb.sisyphus.core.model;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.sisyphus.api.model.AttemptTime;
import com.github.houbb.sisyphus.api.model.RetryAttempt;

import java.util.List;

/**
 * 默认重试信息
 * @author binbin.hou
 * @since 0.0.1
 * @param <R> 泛型
 */
@NotThreadSafe
public class DefaultRetryAttempt<R> implements RetryAttempt<R> {

    /**
     * 执行结果
     */
    private R result;

    /**
     * 尝试次数
     */
    private int attempt;

    /**
     * 尝试次数
     */
    private Throwable cause;

    /**
     * 消耗时间
     */
    private AttemptTime time;

    /**
     * 历史信息
     */
    private List<RetryAttempt<R>> history;

    @Override
    public R result() {
        return result;
    }

    public DefaultRetryAttempt<R> result(R result) {
        this.result = result;
        return this;
    }

    @Override
    public int attempt() {
        return attempt;
    }

    public DefaultRetryAttempt<R> attempt(int attempt) {
        this.attempt = attempt;
        return this;
    }

    @Override
    public Throwable cause() {
        return cause;
    }

    public DefaultRetryAttempt<R> cause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    @Override
    public AttemptTime time() {
        return time;
    }

    public DefaultRetryAttempt<R> time(AttemptTime time) {
        this.time = time;
        return this;
    }

    @Override
    public List<RetryAttempt<R>> history() {
        return history;
    }

    public DefaultRetryAttempt<R> history(List<RetryAttempt<R>> history) {
        this.history = history;
        return this;
    }
}
