package com.github.houbb.sispyhus.test.core;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 时间单位测试
 * @author binbin.hou
 * @since 0.0.7
 */
public class TimeUnitTest {

    @Test
    public void timeUnitHourTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long time = 60;

        // 转化为毫秒
        long millSecondsOne = timeUnit.convert(time, TimeUnit.MILLISECONDS);
        System.out.println(millSecondsOne);
        long millSecondsTwo = TimeUnit.MILLISECONDS.convert(time, timeUnit);
        System.out.println(millSecondsTwo);
    }

}
