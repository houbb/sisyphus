package com.github.houbb.sisyphus.api.support.wait;

import com.github.houbb.sisyphus.api.support.attempt.RetryAttempt;

/**
 * 重试等待策略
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryWait {

    /**
     * 等待时间
     * @param retryAttempt 重试信息
     * @return 等待时间的结果信息
     */
    WaitTime waitTime(final RetryAttempt retryAttempt);

}
