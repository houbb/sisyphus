package com.github.houbb.sisyphus.core.support.pipeline;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.sisyphus.api.support.pipeline.Pipeline;

import java.util.List;

/**
 * 默认的泳道实现
 * @author binbin.hou
 * @since 1.0.0
 */
@NotThreadSafe
public class DefaultPipeline<T> implements Pipeline<T> {

    @Override
    public Pipeline addLast(T t) {
        return null;
    }

    @Override
    public Pipeline addFirst(T t) {
        return null;
    }

    @Override
    public Pipeline replace(int index, T t) {
        return null;
    }

    @Override
    public Pipeline removeLast() {
        return null;
    }

    @Override
    public Pipeline removeFirst() {
        return null;
    }

    @Override
    public Pipeline remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public List<T> slice(int startIndex, int endIndex) {
        return null;
    }
}
