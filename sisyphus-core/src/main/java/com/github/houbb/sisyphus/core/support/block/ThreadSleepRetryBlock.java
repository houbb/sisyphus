package com.github.houbb.sisyphus.core.support.block;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.exception.RetryException;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.wait.WaitTime;

/**
 * 线程沉睡的阻塞方式
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class ThreadSleepRetryBlock implements RetryBlock {

    @Override
    public void block(WaitTime waitTime) {
        try {
            waitTime.unit().sleep(waitTime.time());
        } catch (InterruptedException e) {
            throw new RetryException(e);
        }
    }

}
