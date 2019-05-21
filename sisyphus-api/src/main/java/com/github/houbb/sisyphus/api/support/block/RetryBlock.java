package com.github.houbb.sisyphus.api.support.block;

import com.github.houbb.sisyphus.api.model.WaitTime;

/**
 * 阻塞的方式
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryBlock {

    /**
     * 阻塞方式
     * @param waitTime 等待时间
     */
    void block(final WaitTime waitTime);

}
