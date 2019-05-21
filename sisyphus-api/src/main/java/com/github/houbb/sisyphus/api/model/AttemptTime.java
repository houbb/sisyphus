package com.github.houbb.sisyphus.api.model;

import java.util.Date;

/**
 * 尝试执行接口s
 * @author binbin.hou
 * @since 1.0.0
 */
public interface AttemptTime {

    /**
     * 开始时间
     * @return 开始时间
     */
    Date startTime();

    /**
     * 结束时间
     * @return 结束时间
     */
    Date endTime();

    /**
     * 消耗的时间(毫秒)
     * @return 消耗的时间
     */
    long costTimeInMills();

}
