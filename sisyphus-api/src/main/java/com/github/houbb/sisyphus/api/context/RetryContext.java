package com.github.houbb.sisyphus.api.context;

import com.github.houbb.sisyphus.api.support.block.RetryBlock;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.api.support.stop.RetryStop;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;

import java.util.concurrent.Callable;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryContext<R>  {

    /**
     * 生效条件列表
     * @return 生效条件列表
     */
    RetryCondition condition();

    /**
     * 等待策略列表
     * @return 策略列表
     */
    RetryWait waits();

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

    /**
     * 监听信息列表
     * @return 监听信息列表
     */
    RetryListen listen();

    /**
     * 恢复方式
     * @return 恢复方式
     */
    Recover recover();

}
