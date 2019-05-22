package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;

/**
 * 恒为真重试条件
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class AlwaysTrueRetryCondition implements RetryCondition {

    /**
     * 内部静态类
     */
    private static class AlwaysTrueRetryConditionHolder {
        private static final AlwaysTrueRetryCondition INSTANCE = new AlwaysTrueRetryCondition();
    }

    /**
     * 获取单例
     * @return 单例
     */
    public static RetryCondition getInstance() {
        return AlwaysTrueRetryConditionHolder.INSTANCE;
    }

    @Override
    public boolean condition(RetryAttempt retryAttempt) {
        return true;
    }

}
