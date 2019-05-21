package com.github.houbb.sisyphus.core.support.stop;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;

/**
 * 最大尝试次数终止策略
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class MaxAttemptRetryStop implements RetryStop {

    /**
     * 最大重试次数
     */
    private final int maxAttempt;

    public MaxAttemptRetryStop(int maxAttempt) {
        this.maxAttempt = maxAttempt;
    }


    @Override
    public boolean stop(RetryAttempt attempt) {
        int times = attempt.number();
        return times >= maxAttempt;
    }

}
