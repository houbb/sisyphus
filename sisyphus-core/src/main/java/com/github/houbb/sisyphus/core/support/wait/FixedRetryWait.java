package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

import java.util.concurrent.TimeUnit;

/**
 * 固定时间间隔等待
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class FixedRetryWait implements RetryWait {

    /**
     * 毫秒
     */
    private final int timeInMills;

    public FixedRetryWait(int timeInMills) {
        this.timeInMills = timeInMills;
    }

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        return new DefaultWaitTime(timeInMills, TimeUnit.MILLISECONDS);
    }

}
