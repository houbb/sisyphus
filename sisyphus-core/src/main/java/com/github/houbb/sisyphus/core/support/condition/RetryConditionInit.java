package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.pipeline.Pipeline;
import com.github.houbb.sisyphus.core.support.pipeline.DefaultPipeline;

import java.util.List;

/**
 * 重试条件初始化类
 * 1. 满足任意一个即可
 * 2. 如果有更加复杂的需求，用户应该自定定义。
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public abstract class RetryConditionInit<R> implements RetryCondition<R> {

    @Override
    public boolean condition(RetryAttempt<R> retryAttempt) {
        Pipeline<RetryCondition<R>> pipeline = new DefaultPipeline<>();
        this.init(pipeline, retryAttempt);

        List<RetryCondition<R>> retryConditions = pipeline.list();
        for (RetryCondition<R> condition : retryConditions) {
            if (condition.condition(retryAttempt)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化列表
     *
     * @param pipeline     当前列表泳道
     * @param retryAttempt 执行信息
     */
    protected abstract void init(final Pipeline<RetryCondition<R>> pipeline,
                                 final RetryAttempt retryAttempt);

}
