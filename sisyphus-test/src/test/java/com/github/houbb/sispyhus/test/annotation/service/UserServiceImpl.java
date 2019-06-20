package com.github.houbb.sispyhus.test.annotation.service;

import com.github.houbb.sisyphus.annotation.annotation.Retry;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class UserServiceImpl implements UserService{

    @Retry
    @Override
    public void queryUser(long id) {
        System.out.println("查询用户...");
        throw new RuntimeException();
    }

}
