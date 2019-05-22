package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

/**
 * 恒为假重试条件
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class AlwaysFalseRetryCondition implements RetryCondition {

    /**
     * 内部静态类
     */
    private static class AlwaysFalseRetryConditionHolder {
        private static final AlwaysFalseRetryCondition INSTANCE = new AlwaysFalseRetryCondition();
    }

    /**
     * 获取单例
     * @return 单例
     */
    public static RetryCondition getInstance() {
        return AlwaysFalseRetryConditionHolder.INSTANCE;
    }

    @Override
    public boolean condition(RetryAttempt retryAttempt) {
        return false;
    }
    
}
