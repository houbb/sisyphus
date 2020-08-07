package com.github.houbb.sisyphus.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author binbin.hou
 * @since 0.0.9
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github.houbb.sisyphus.test.service")
public class SisyphusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusApplication.class, args);
    }

}
