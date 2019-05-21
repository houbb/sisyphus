package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

/**
 * 根据结果进行重试的抽象类
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public abstract class AbstractResultRetryCondition<R> implements RetryCondition<R> {

    @Override
    public boolean condition(RetryAttempt<R> retryAttempt) {
        return resultCondition(retryAttempt.result());
    }

    /**
     * 对结果进行判断
     * @param result 结果信息
     * @return 对结果进行判断
     */
    protected abstract boolean resultCondition(final R result);

    /**
     * 判断是否有结果信息
     * 1. 有，返回 true
     * 2. 无，返回 false
     * @return 是否有结果
     */
    protected boolean hasResult(final R result) {
        return ObjectUtil.isNotNull(result);
    }

}
