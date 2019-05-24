package com.github.houbb.sisyphus.annotation.model;

import com.github.houbb.sisyphus.annotation.annotation.metadata.RetryAble;

import java.lang.annotation.Annotation;

/**
 * 可重试注解对象
 * @author binbin.hou
 * @since 0.0.5
 */
public class RetryAbleBean {

    /**
     * 注解信息
     */
    private RetryAble retryAble;

    /**
     * 原始注解信息
     */
    private Annotation annotation;

    public RetryAble retryAble() {
        return retryAble;
    }

    public RetryAbleBean retryAble(RetryAble retryAble) {
        this.retryAble = retryAble;
        return this;
    }

    public Annotation annotation() {
        return annotation;
    }

    public RetryAbleBean annotation(Annotation annotation) {
        this.annotation = annotation;
        return this;
    }
}
