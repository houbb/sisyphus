package com.github.houbb.sisyphus.api.core;

import com.github.houbb.sisyphus.api.context.RecoverContext;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface Recover<R> {

    /**
     * 执行恢复
     * @param context 上下文
     * @return 执行结果
     */
    R recover(final RecoverContext context);

}
