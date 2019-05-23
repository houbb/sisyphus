package com.github.houbb.sisyphus.spring.annotation;

import com.github.houbb.sisyphus.spring.config.RetryAopConfig;
import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用重试注解
 * @author binbin.hou
 * @since 0.0.4
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RetryAopConfig.class)
public @interface EnableRetry {
}
