package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机等待策略
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class RandomRetryWait implements RetryWait {

    /**
     * 最小时间(毫秒)
     * 要求：必须为正数
     */
    private final long minMills;

    /**
     * 最大时间(毫秒)
     * 要求：比如大于 {@link #minMills} 最小的时间
     */
    private final long maxMills;

    public RandomRetryWait(long minMills, long maxMills) {
        this.minMills = minMills;
        this.maxMills = maxMills;
    }

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long time = random.nextLong(minMills, maxMills-minMills);
        return new DefaultWaitTime(time);
    }

}
