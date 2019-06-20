package com.github.houbb.sispyhus.test.annotation.core;

import com.github.houbb.sispyhus.test.annotation.service.MenuServiceImpl;
import com.github.houbb.sisyphus.annotation.core.RetryTemplate;

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

}
