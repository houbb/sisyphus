package com.github.houbb.sisyphus.api.support.listen;

import com.github.houbb.sisyphus.api.model.RetryAttempt;

/**
 * 重试监听接口
 *
 * 注意：实现类应该有无参构造函数
 * @author binbin.hou
 * @since 0.0.1
 */
public interface RetryListen {

    /**
     * 执行重试监听
     * @param attempt 重试
     * @param <R> 泛型
     */
    <R> void listen(final RetryAttempt<R> attempt);

}
