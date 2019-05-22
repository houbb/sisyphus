package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

import java.util.List;

/**
 * 重试条件初始化类
 * 1. 满足任意一个即可
 * 2. 如果有更加复杂的需求，用户应该自定定义。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public abstract class AbstractRetryConditionInit implements RetryCondition {

    @Override
    public boolean condition(RetryAttempt retryAttempt) {
        Pipeline<RetryCondition> pipeline = new DefaultPipeline<>();
        this.init(pipeline, retryAttempt);

        List<RetryCondition> retryConditions = pipeline.list();
        for (RetryCondition condition : retryConditions) {
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
    protected abstract void init(final Pipeline<RetryCondition> pipeline,
                                 final RetryAttempt retryAttempt);

}
