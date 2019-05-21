package com.github.houbb.sisyphus.core.support.recover;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.recover.Recover;

/**
 * 不指定任何动作
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NoRecover implements Recover {
    @Override
    public <R> void recover(RetryAttempt<R> retryAttempt) {

    }
}
