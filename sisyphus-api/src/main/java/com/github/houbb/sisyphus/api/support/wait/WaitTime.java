package com.github.houbb.sisyphus.api.support.wait;

import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface WaitTime {

    /**
     * 等待时间
     * @return 时间
     */
    long time();

    /**
     * 等待时间单位
     * @return 时间单位
     */
    TimeUnit unit();

}
