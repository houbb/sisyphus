package com.github.houbb.sisyphus.api.context;

import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

/**
 * 重试等待上下文
 * @author binbin.hou
 * @since 0.0.3
 */
public interface RetryWaitContext<R> extends RetryAttempt {

    /**
     * 基础值（毫秒）
     * 1. fixed: 固定间隔
     * 2. 递增/指数：为初始值
     * 3. random/noRetry 这个值会被忽略
     * @return 基础值
     */
    long value();

    /**
     * 最小等待时间（毫秒）
     * @return 最小等待时间（毫秒）
     */
    long min();

    /**
     * 最大等待时间（毫秒）
     * @return 最大等待时间（毫秒）
     */
    long max();

    /**
     * 变换因子（递增/毫秒）
     * 1. 递增：每次增加的时间
     * 2. 指数：每次乘的因数
     * @return 变换因子
     */
    double factor();

    /**
     * 对应的 class 信息
     * @return class 信息
     */
    Class<? extends RetryWait> retryWait();

}
