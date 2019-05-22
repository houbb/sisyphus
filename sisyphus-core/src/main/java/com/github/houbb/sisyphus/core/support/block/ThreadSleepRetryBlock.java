package com.github.houbb.sisyphus.core.support.block;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.sisyphus.api.exception.RetryException;
import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.block.RetryBlock;

/**
 * 线程沉睡的阻塞方式
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ThreadSleepRetryBlock implements RetryBlock {

    /**
     * 获取单例
     * @return 获取单例
     */
    public static RetryBlock getInstance() {
        return InstanceFactory.getInstance().singleton(ThreadSleepRetryBlock.class);
    }

    @Override
    public void block(WaitTime waitTime) {
        try {
            waitTime.unit().sleep(waitTime.time());
        } catch (InterruptedException e) {
            //restore status
            Thread.currentThread().interrupt();

            throw new RetryException(e);
        }
    }

}
