package com.github.houbb.sisyphus.core.model;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.WaitTime;

import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class DefaultWaitTime implements WaitTime {

    /**
     * 等待时间
     */
    private final long time;

    /**
     * 时间单位
     */
    private final TimeUnit unit;

    public DefaultWaitTime(long time) {
        this.time = time;
        this.unit = TimeUnit.MILLISECONDS;
    }

    public DefaultWaitTime(long time, TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    @Override
    public long time() {
        return this.time;
    }

    @Override
    public TimeUnit unit() {
        return this.unit;
    }

}
