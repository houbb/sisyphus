package com.github.houbb.sisyphus.core.support.listen;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;

/**
 * 不进行任何监听动作
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NoRetryListen implements RetryListen {

    @Override
    public <R> void listen(RetryAttempt<R> attempt) {

    }

}
