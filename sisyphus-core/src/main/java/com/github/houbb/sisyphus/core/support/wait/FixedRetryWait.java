package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.WaitTime;

/**
 * 固定时间间隔等待
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class FixedRetryWait extends AbstractRetryWait {

    @Override
    public WaitTime waitTime(RetryWaitContext retryWaitContext) {
        return super.rangeCorrect(retryWaitContext.value(), retryWaitContext.min(), retryWaitContext.max());
    }

}
