package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.support.wait.WaitTime;

import java.util.concurrent.TimeUnit;

/**
 * 默认等待时间
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class DefaultWaitTime implements WaitTime {

    /**
     * 时间
     */
    private final long time;

    /**
     * 单位
     */
    private final TimeUnit unit;

    public DefaultWaitTime(long time, TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    @Override
    public long time() {
        return time;
    }

    @Override
    public TimeUnit unit() {
        return unit;
    }

}
