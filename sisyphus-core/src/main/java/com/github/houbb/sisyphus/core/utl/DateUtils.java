package com.github.houbb.sisyphus.core.utl;

import java.util.Date;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class DateUtils {

    /**
     * 返回当前时间
     * @return 当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 计算消耗的毫秒
     * @param start 开始时间
     * @param end 结束时间
     * @return 结果
     */
    public static long costTimeInMills(final Date start, final Date end) {
        return end.getTime() - start.getTime();
    }

}
