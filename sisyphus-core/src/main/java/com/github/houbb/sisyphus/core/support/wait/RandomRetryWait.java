package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.model.WaitTime;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机等待策略
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class RandomRetryWait extends AbstractRetryWait {

    @Override
    public WaitTime waitTime(RetryWaitContext retryWaitContext) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long time = random.nextLong(retryWaitContext.min(), retryWaitContext.max()-retryWaitContext.min());
        return super.rangeCorrect(time, retryWaitContext.min(), retryWaitContext.max());
    }

}
