package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

import java.util.concurrent.TimeUnit;

/**
 * 无时间等待
 * 1. 不是很建议使用这种方式
 * 2. 一般的异常都有时间性，在一定区间内有问题，那就是有问题。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NoRetryWait implements RetryWait {

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        return new DefaultWaitTime(0, TimeUnit.MILLISECONDS);
    }

}
