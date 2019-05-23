package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

/**
 * 无时间等待
 * 1. 不是很建议使用这种方式
 * 2. 一般的异常都有时间性，在一定区间内有问题，那就是有问题。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NoRetryWait extends AbstractRetryWait {

    /**
     * 获取一个单例示例
     * @return 单例示例
     */
    public static RetryWait getInstance() {
        return InstanceFactory.getInstance().singleton(NoRetryWait.class);
    }

    @Override
    public WaitTime waitTime(RetryWaitContext retryWaitContext) {
        return super.rangeCorrect(0, retryWaitContext.min(), retryWaitContext.max());
    }

}
