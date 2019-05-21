package com.github.houbb.sisyphus.api.support.wait;

import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;

/**
 * 重试等待策略
 * @author binbin.hou
 * @since 0.0.1
 */
public interface RetryWait {

    /**
     * 等待时间
     * @param retryAttempt 重试信息
     * @return 等待时间的结果信息
     */
    WaitTime waitTime(final RetryAttempt retryAttempt);

}
