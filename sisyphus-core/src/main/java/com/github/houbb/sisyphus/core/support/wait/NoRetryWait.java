package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.api.support.wait.WaitTime;

import java.util.concurrent.TimeUnit;

/**
 * 无时间等待
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class NoRetryWait implements RetryWait {

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        return new DefaultWaitTime(0, TimeUnit.MILLISECONDS);
    }

}
