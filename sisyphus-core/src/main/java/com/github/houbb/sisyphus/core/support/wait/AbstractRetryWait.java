package com.github.houbb.sisyphus.core.support.wait;

import com.github.houbb.sisyphus.api.model.WaitTime;
import com.github.houbb.sisyphus.api.support.wait.RetryWait;
import com.github.houbb.sisyphus.core.model.DefaultWaitTime;

/**
 * 抽象重试时间等待
 * @author binbin.hou
 * @since 0.0.3
 */
public abstract class AbstractRetryWait implements RetryWait {

    /**
     * 修正范围
     * @param timeMills 结果
     * @param min 最小值
     * @param max 最大值
     * @return 修正范围
     */
    protected WaitTime rangeCorrect(final long timeMills, final long min, final long max) {
        long resultMills = timeMills;
        if(timeMills > max) {
            resultMills = max;
        }
        if(timeMills < min) {
            resultMills = min;
        }
        return new DefaultWaitTime(resultMills);
    }

}
