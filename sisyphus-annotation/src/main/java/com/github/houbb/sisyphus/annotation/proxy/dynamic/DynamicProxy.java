package com.github.houbb.sisyphus.annotation.proxy.dynamic;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.proxy.dynamic.BaseDynamicProxy;
import com.github.houbb.sisyphus.annotation.proxy.RetryMethodHandler;

import java.lang.reflect.Method;

/**
 * 动态代理
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class DynamicProxy extends BaseDynamicProxy {

    /**
     * 目标对象
     */
    private final Object target;

    public DynamicProxy(Object target) {
        super(target);
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return InstanceFactory.getInstance().threadSafe(RetryMethodHandler.class)
                .handle(target, method, args);
    }

}
