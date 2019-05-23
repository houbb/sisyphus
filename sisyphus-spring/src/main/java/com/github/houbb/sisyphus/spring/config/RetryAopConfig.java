package com.github.houbb.sisyphus.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 重试 aop 配置
 * @author binbin.hou
 * @since 0.0.4
 */
@Configuration
@ComponentScan(basePackages = "com.github.houbb.sisyphus.spring")
public class RetryAopConfig {
}
