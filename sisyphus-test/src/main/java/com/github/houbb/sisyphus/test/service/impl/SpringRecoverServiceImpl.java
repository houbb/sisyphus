package com.github.houbb.sisyphus.test.service.impl;

import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.test.service.SpringRecoverService;
import com.github.houbb.sisyphus.test.support.recover.MyRecover;
import org.springframework.stereotype.Service;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Service
public class SpringRecoverServiceImpl implements SpringRecoverService {

    @Override
    @Retry(recover = MyRecover.class)
    public String query(String email) {
        System.out.println("spring service query..." + email);
        throw new RuntimeException();
    }

}
