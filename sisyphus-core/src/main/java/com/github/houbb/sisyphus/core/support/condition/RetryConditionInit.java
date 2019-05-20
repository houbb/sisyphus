package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * 重试条件初始化类
 * @author binbin.hou
 * @since 1.0.0
 */
public abstract class RetryConditionInit implements RetryCondition {

    /**
     * 重试列表
     * 针对某一个实例化对象的泳道。
     * 1. addLast();
     * 2. addFirst();
     */
    private List<RetryCondition> conditionList = new ArrayList<>();

    @Override
    public boolean condition(RetryAttempt retryAttempt) {
        return false;
    }

    /**
     * 初始化列表
     * @param conditionList 当前列表
     * @param retryAttempt 执行信息
     */
    protected abstract void init(final List<RetryCondition> conditionList,
                        final RetryAttempt retryAttempt);

}
