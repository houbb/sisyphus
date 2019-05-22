package com.github.houbb.sisyphus.core.support.recover;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.recover.Recover;

/**
 * 不指定任何动作
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NoRecover implements Recover {

    /**
     * 获取一个单例示例
     * @return 单例示例
     */
    public static Recover getInstance() {
        return InstanceFactory.getInstance().singleton(NoRecover.class);
    }

    @Override
    public <R> void recover(RetryAttempt<R> retryAttempt) {

    }
}
