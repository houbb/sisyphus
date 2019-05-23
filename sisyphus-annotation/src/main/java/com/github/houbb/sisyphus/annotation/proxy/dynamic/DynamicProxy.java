package com.github.houbb.sisyphus.annotation.proxy.dynamic;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.proxy.IProxy;
import com.github.houbb.sisyphus.annotation.proxy.RetryMethodHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class DynamicProxy implements InvocationHandler, IProxy {

    /**
     * 目标对象
     */
    private final Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return InstanceFactory.getInstance().threadSafe(RetryMethodHandler.class)
                    .handle(target, method, args);
        } catch (InvocationTargetException ex) {
            // 程序内部没有处理的异常
            throw ex.getTargetException();
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    @Override
    public Object proxy() {
        // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(target);

        return Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
    }
}
