package com.github.houbb.sispyhus.test.spring;

import com.github.houbb.sisyphus.test.config.SpringConfig;
import com.github.houbb.sisyphus.test.service.SpringRecoverService;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringRecoverServiceTest {

    @Autowired
    private SpringRecoverService springRecoverService;

    @Test(expected = RuntimeException.class)
    public void queryTest() {
        String email = "老马的邮箱@gmail.com";
        springRecoverService.query(email);
    }

}
