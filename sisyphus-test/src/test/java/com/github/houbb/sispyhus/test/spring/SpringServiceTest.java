package com.github.houbb.sispyhus.test.spring;

import com.github.houbb.sisyphus.test.config.SpringConfig;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringServiceTest {

    @Autowired
    private SpringService springService;

    @Test(expected = RuntimeException.class)
    public void queryTest() {
        springService.query();
    }

}
