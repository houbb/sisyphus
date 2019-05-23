package com.github.houbb.sispyhus.test.annotation.service;

import com.github.houbb.sisyphus.annotation.annotation.Retry;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class MenuServiceImpl {

    public void queryMenu(long id) {
        System.out.println("查询菜单...");
        throw new RuntimeException();
    }

    @Retry
    public void queryMenuRetry(long id) {
        System.out.println("查询菜单...");
        throw new RuntimeException();
    }

}
