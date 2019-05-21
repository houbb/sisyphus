package com.github.houbb.sisyphus.core.model;

import com.github.houbb.sisyphus.api.model.AttemptTime;

import java.util.Date;

/**
 * 尝试执行的时候消耗时间
 * @author binbin.hou
 * @since 1.0.0
 */
public class DefaultAttemptTime implements AttemptTime {

    @Override
    public Date startTime() {
        return null;
    }

    @Override
    public Date endTime() {
        return null;
    }

    @Override
    public long costTimeInMills() {
        return 0;
    }
//
//    /**
//     * 开始时间
//     * @return 开始时间
//     */
//    Date startTime();
//
//    /**
//     * 结束时间
//     * @return 结束时间
//     */
//    Date endTime();
//
//    /**
//     * 消耗的时间(毫秒)
//     * @return 消耗的时间
//     */
//    long costTimeInMills();

}
