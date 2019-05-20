package com.github.houbb.sisyphus.api.support.attempt;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface RetryAttempt<R> {

    /**
     * 获取方法执行的结果
     * @return 执行的结果
     */
    R result();

    /**
     * 当前执行次数
     * @return 次数
     */
    int times();

    /**
     * 异常信息
     * @return 异常信息
     */
    Throwable cause();

    /**
     * 重试的历史信息
     * @return 重试的历史列表
     */
    List<RetryAttempt> history();

}
