package com.github.houbb.sispyhus.test.core;

import com.github.houbb.sisyphus.core.core.Retryer;
import com.github.houbb.sisyphus.core.support.condition.RetryConditions;
import org.junit.Test;

import java.util.concurrent.Callable;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class RetryerTest {

    @Test
    public void helloTest() {
        Retryer.<String>newInstance()
                .condition(RetryConditions.isEqualsResult("fail"))
                .retry(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("called...");
                        return "fail";
                    }
                });
    }

}
