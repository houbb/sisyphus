package com.github.houbb.sisyphus.api.context;

import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author binbin.hou
 * @since 1.0.0
 * @param <R> 可执行方法的泛型
 */
public interface RetryContext<R> {

    /**
     * 生效条件列表
     * @return 生效条件列表
     */
    List<RetryCondition> conditions();


    /**
     * 等待策略列表
     * @return 策略列表
     */
    List<RetryWait> waits();

    /**
     * 阻塞方式
     * @return 阻塞方式
     */
    RetryBlock block();


    /**
     * 停止方式
     * @return 停止方式
     */
    RetryStop stop();


    /**
     * 可执行的方法
     * @return 方法
     */
    Callable<R> callable();

}
