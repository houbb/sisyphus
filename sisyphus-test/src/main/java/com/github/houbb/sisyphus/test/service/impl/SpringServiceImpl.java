package com.github.houbb.sisyphus.test.service.impl;

import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.springframework.stereotype.Service;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
@Service
public class SpringServiceImpl implements SpringService {

    @Override
    @Retry
    public String query() {
        System.out.println("spring service query...");
        throw new RuntimeException();
    }

}
