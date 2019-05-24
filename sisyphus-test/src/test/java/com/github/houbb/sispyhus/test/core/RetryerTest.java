package com.github.houbb.sispyhus.test.core;

import com.github.houbb.sisyphus.core.core.Retryer;
import org.junit.Test;

import java.util.concurrent.Callable;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class RetryerTest {

    /**
     * 不会触发重试
     */
    @Test
    public void commonTest() {
        Retryer.<String>newInstance()
                .callable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("called...");
                        return null;
                    }
                }).retryCall();
    }

    /**
     * 默认异常进行重试
     */
    @Test(expected = RuntimeException.class)
    public void helloTest() {
        Retryer.<String>newInstance()
                .callable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("called...");
                        throw new RuntimeException();
                    }
                }).retryCall();
    }

}
