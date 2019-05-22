package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;

/**
 * 非空结果重试条件
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class NotNullResultRetryCondition<R> extends AbstractResultRetryCondition<R> {
    @Override
    protected boolean resultCondition(R result) {
        return ObjectUtil.isNotNull(result);
    }
}
