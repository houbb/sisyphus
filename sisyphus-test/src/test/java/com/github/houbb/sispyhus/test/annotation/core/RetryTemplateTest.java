package com.github.houbb.sispyhus.test.annotation.core;

import com.github.houbb.heaven.support.proxy.none.NoneProxy;
import com.github.houbb.sispyhus.test.annotation.service.MenuServiceImpl;
import com.github.houbb.sispyhus.test.annotation.service.UserService;
import com.github.houbb.sispyhus.test.annotation.service.UserServiceImpl;
import com.github.houbb.sisyphus.annotation.core.RetryTemplate;
import com.github.houbb.sisyphus.annotation.proxy.cglib.CglibProxy;
import com.github.houbb.sisyphus.annotation.proxy.dynamic.DynamicProxy;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class RetryTemplateTest {

    @Test(expected = RuntimeException.class)
    public void templateTest() {
        MenuServiceImpl menuService = RetryTemplate.getProxyObject(new MenuServiceImpl());
        menuService.queryMenu(1);
    }

    @Test(expected = RuntimeException.class)
    public void templateRetryTest() {
        MenuServiceImpl menuService = RetryTemplate.getProxyObject(new MenuServiceImpl());
        menuService.queryMenuRetry(1);
    }

    @Test(expected = RuntimeException.class)
    public void noProxyTest() {
        MenuServiceImpl menuService = (MenuServiceImpl) new NoneProxy(new MenuServiceImpl()).proxy();
        menuService.queryMenu(1);
    }

    @Test(expected = RuntimeException.class)
    public void dynamicProxyTest() {
        UserService userService = (UserService) new DynamicProxy(new UserServiceImpl()).proxy();
        userService.queryUser(1);
    }

    @Test(expected = RuntimeException.class)
    public void cglibProxyTest() {
        UserService userService = (UserService) new CglibProxy(new UserServiceImpl()).proxy();
        userService.queryUser(1);
    }

}
