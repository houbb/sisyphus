package com.github.houbb.sisyphus.core.support.listen;

import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;

/**
 * 监听器工具类
 * @author binbin.hou
 * @since 0.0.2
 */
public final class RetryListens {

    private RetryListens(){}

    /**
     * 不执行任何监听
     * @return 不执行任何监听
     */
    public static RetryListen noListen() {
        return InstanceFactory.getInstance().singleton(NoRetryListen.class);
    }

    /**
     * 指定多个监听器
     * @param retryListens 监听器信息
     * @return 结果
     */
    public static RetryListen listens(final RetryListen ... retryListens) {
        if(ArrayUtil.isEmpty(retryListens)) {
            return noListen();
        }
        return new AbstractRetryListenInit() {
            @Override
            protected void init(Pipeline<RetryListen> pipeline, RetryAttempt attempt) {
                for(RetryListen retryListen : retryListens) {
                    pipeline.addLast(retryListen);
                }
            }
        };
    }

}
