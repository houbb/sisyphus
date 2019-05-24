package com.github.houbb.sisyphus.test.config;

import com.github.houbb.sisyphus.spring.annotation.EnableRetry;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
@Configurable
@ComponentScan(basePackages = "com.github.houbb.sisyphus.test.service")
@EnableRetry
public class SpringConfig {
}
