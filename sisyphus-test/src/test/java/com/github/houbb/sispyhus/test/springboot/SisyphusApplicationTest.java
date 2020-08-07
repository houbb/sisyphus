package com.github.houbb.sispyhus.test.springboot;

import com.github.houbb.sisyphus.test.SisyphusApplication;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SisyphusApplication.class)
public class SisyphusApplicationTest {

    @Autowired
    private SpringService springService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void beanTest() {
        boolean contains = applicationContext.containsBean("springConfig");
        Assert.assertFalse(contains);
    }

    @Test(expected = RuntimeException.class)
    public void queryTest() {
        springService.query();
    }

}
