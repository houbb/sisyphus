package com.github.houbb.sisyphus.annotation.core;

import com.github.houbb.heaven.constant.enums.ProxyTypeEnum;
import com.github.houbb.heaven.support.proxy.ProxyFactory;
import com.github.houbb.heaven.support.proxy.none.NoneProxy;
import com.github.houbb.sisyphus.annotation.proxy.cglib.CglibProxy;
import com.github.houbb.sisyphus.annotation.proxy.dynamic.DynamicProxy;

/**
 * 重试模板
 * @author binbin.hou
 * @since 0.0.3
 */
public final class RetryTemplate {

    private RetryTemplate(){}

    /**
     * 根据对象获取对应的代理
     *
     * @param object 原始对象
     * @return 对应的代理对象
     */
    public static <R> R getProxyObject(final R object) {
        final ProxyTypeEnum proxyTypeEnum = ProxyFactory.getProxyType(object);
        if (ProxyTypeEnum.NONE.equals(proxyTypeEnum)) {
            return (R) new NoneProxy(object).proxy();
        } else if (ProxyTypeEnum.DYNAMIC.equals(proxyTypeEnum)) {
            return (R) new DynamicProxy(object).proxy();
        } else {
            return (R) new CglibProxy(object).proxy();
        }
    }

}
