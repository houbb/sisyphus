package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.WaitTime;

/**
 * 递增策略
 * @since 0.0.1
 * @author binbin.hou
 */
@ThreadSafe
public class IncreaseRetryWait extends AbstractRetryWait {

    @Override
    public WaitTime waitTime(RetryWaitContext retryWaitContext) {
        final int previousAttempt = retryWaitContext.attempt()-1;
        long result = Math.round(retryWaitContext.value() + previousAttempt*retryWaitContext.factor());

        return super.rangeCorrect(result, retryWaitContext.min(), retryWaitContext.max());
    }

}
