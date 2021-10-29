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

    /**
     * 请求参数
     * @since 0.1.0
     */
    private Object[] args;

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

    public Object[] args() {
        return args;
    }

    public RetryAbleBean args(Object[] args) {
        this.args = args;
        return this;
    }
}
