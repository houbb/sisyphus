package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

/**
 * 递增策略
 * @since 0.0.1
 * @author binbin.hou
 */
@ThreadSafe
public class IncreaseRetryWait implements RetryWait {

    /**
     * 初始化等待时间
     * 1. 非负数
     * 2. 最特殊的情况：为0是允许的。
     * 如果初始值为0，增长也等于0，其实就相当于没有任何等待。
     */
    private final long initMills;

    /**
     * 每次增加的等待时间
     * 1. 允许为负值。如果最后的结果 <=0，则使用 0
     */
    private final long increaseMills;

    public IncreaseRetryWait(long initMills, long increaseMills) {
        // 参数校验
        ArgUtil.notNegative(initMills, "initMills");

        this.initMills = initMills;
        this.increaseMills = increaseMills;
    }

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        final int previousAttempt = retryAttempt.attempt()-1;
        long result = initMills + previousAttempt*increaseMills;
        if(result <= 0) {
            result = 0L;
        }
        return new DefaultWaitTime(result);
    }

}
