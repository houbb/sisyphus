package com.github.houbb.sisyphus.springboot.starter.config;

import com.github.houbb.sisyphus.spring.annotation.EnableRetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * 重试自动配置
 * @author binbin.hou
 * @since 0.0.9
 */
@EnableRetry
@Configuration
@ConditionalOnClass(EnableRetry.class)
public class SisyphusAutoConfig {
}
