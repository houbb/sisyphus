package com.github.houbb.sisyphus.api.annotation;

import java.lang.annotation.*;

/**
 * 现场恢复注解
 * 迁移到 annotation
 * @author binbin.hou
 * @since 0.0.1
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Deprecated
public @interface Recover {
}
