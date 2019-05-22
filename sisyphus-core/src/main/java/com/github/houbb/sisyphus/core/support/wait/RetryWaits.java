package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

/**
 * 重试等待策略工具类
 * @author binbin.hou
 * @since 0.0.2
 */
public final class RetryWaits {

    /**
     * 指定多个等待策略
     * @param retryWaits 等待策略
     * @return 等待策略
     */
    public static RetryWait waits(final RetryWait ... retryWaits) {
        if(ArrayUtil.isEmpty(retryWaits)) {
            return InstanceFactory.getInstance().singleton(NoRetryWait.class);
        }

        return new AbstractRetryWaitInit() {
            @Override
            protected void init(Pipeline<RetryWait> pipeline, RetryAttempt retryAttempt) {
                for(RetryWait retryWait : retryWaits) {
                    pipeline.addFirst(retryWait);
                }
            }
        };
    }

}
