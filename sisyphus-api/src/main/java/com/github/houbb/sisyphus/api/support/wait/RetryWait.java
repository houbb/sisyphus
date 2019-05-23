package com.github.houbb.sisyphus.api.support.wait;

import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.model.WaitTime;

/**
 * 重试等待策略
 * 1. 所有的实现必须要有无参构造器
 * @author binbin.hou
 * @since 0.0.1
 */
public interface RetryWait {

    /**
     * 等待时间
     * @param retryWaitContext 上下文信息
     * @return 等待时间的结果信息
     * @since 0.0.3 参数调整
     */
    WaitTime waitTime(final RetryWaitContext retryWaitContext);

}
