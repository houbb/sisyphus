package com.github.houbb.sisyphus.api.support.recover;

import com.github.houbb.sisyphus.api.model.RetryAttempt;

/**
 * 恢复现场接口
 * 1. 只会在所有的尝试都执行完成之后才会执行。
 * 2. 触发了重试，且所有的重试都完成了，但结果依然是失败。
 * 3. 根据实际使用，一次失败对应的 recover 应该是唯一的，不然复杂度会没完没了了的扩散。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface Recover {

    /**
     * 执行恢复
     * @param retryAttempt 重试信息
     * @param <R> 泛型
     */
    <R> void recover(final RetryAttempt<R> retryAttempt);

}
