package com.github.houbb.sisyphus.core.support.condition;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;

/**
 * 有异常则触发重试
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class ExceptionCauseRetryCondition extends AbstractCauseRetryCondition {

    @Override
    protected boolean causeCondition(Throwable throwable) {
        return ObjectUtil.isNotNull(throwable);
    }

}
