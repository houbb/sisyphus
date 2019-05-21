package com.github.houbb.sisyphus.core.utl;

import com.github.houbb.heaven.annotation.CommonEager;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@CommonEager
public class ArgUtils {

    /**
     * 断言为正整数
     * @param number 入参
     * @param paramName 参数名称
     */
    public static void postiveInt(final int number, final String paramName) {
        if(number <= 0) {
            throw new IllegalArgumentException(paramName + " must be > 0!");
        }
    }

    /**
     * 断言为非负整数
     * @param number 入参
     * @param paramName 参数名称
     */
    public static void notNegtiveInt(final int number, final String paramName) {
        if(number < 0) {
            throw new IllegalArgumentException(paramName + " must be >= 0!");
        }
    }

}
