package com.github.houbb.sisyphus.core.core;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.core.Retry;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@ThreadSafe
public class DefaultRetry<R> implements Retry<R> {

    @Override
    public R retry(RetryContext context) {
        return null;
    }

}
