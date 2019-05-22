package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

/**
 * 指数增长的等待策略
 * 1. 如果因数大于 1 越来越快。
 * 2. 如果因数等于1 保持不变
 * 3. 如果因数大于0，且小于1 。越来越慢
 *
 * 斐波那契数列就是一种乘数接近于：1.618 的黄金递增。
 * 可以参考 {@link com.github.houbb.heaven.constant.MathConst#GOLD_SECTION}
 * 指数等待函数
 * @author binbin.hou
 * @since 0.0.1
 */
public class ExponentialRetryWait implements RetryWait {

    /**
     * 初始化时间
     * 必须为非负数
     */
    private final long initMills;

    /**
     * 因数：必须为非负数
     * 为了更加灵活，
     * （1）允许为0
     * （2）允许为小数，乘的结果四舍五入。
     */
    private final double multiplier;

    /**
     * 最大时间
     * 1. 必须非负数
     */
    private final long maxMills;

    public ExponentialRetryWait(long initMills, double multiplier, long maxMills) {
        ArgUtil.notNegative(initMills, "initMills");
        ArgUtil.notNegative(multiplier, "multiplier");
        ArgUtil.notNegative(maxMills, "maxMills");

        this.initMills = initMills;
        this.multiplier = multiplier;
        this.maxMills = maxMills;
    }

    @Override
    public WaitTime waitTime(RetryAttempt retryAttempt) {
        final int previousAttempt = retryAttempt.attempt()-1;
        double exp = Math.pow(multiplier, previousAttempt);
        long result = Math.round(initMills * exp);

        if (result > maxMills) {
            result = maxMills;
        }
        result = result >= 0L ? result : 0L;

        return new DefaultWaitTime(result);
    }

}
