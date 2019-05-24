package com.github.houbb.sisyphus.annotation.handler.method;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.proxy.IMethodHandler;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.annotation.annotation.RetryWait;
import com.github.houbb.sisyphus.annotation.annotation.metadata.RetryAble;
import com.github.houbb.sisyphus.annotation.handler.RetryAbleHandler;
import com.github.houbb.sisyphus.annotation.model.RetryAbleBean;
import com.github.houbb.sisyphus.api.context.RetryContext;
import com.github.houbb.sisyphus.api.context.RetryWaitContext;
import com.github.houbb.sisyphus.api.support.condition.RetryCondition;
import com.github.houbb.sisyphus.api.support.listen.RetryListen;
import com.github.houbb.sisyphus.api.support.recover.Recover;
import com.github.houbb.sisyphus.core.core.RetryWaiter;
import com.github.houbb.sisyphus.core.core.Retryer;
import com.github.houbb.sisyphus.core.support.wait.NoRetryWait;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 默认的重试方法实现
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class RetryMethodHandler implements IMethodHandler {

    @Override
    public Object handle(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 判断注解信息
        Optional<RetryAbleBean> retryAbleAnnotationOpt = retryAbleAnnotation(method);
        if(!retryAbleAnnotationOpt.isPresent()) {
            return method.invoke(proxy, args);
        }

        //2. 包含注解才进行处理
        final RetryAbleBean retryAbleBean = retryAbleAnnotationOpt.get();
        final Callable callable = buildCallable(proxy, method, args);
        final RetryAbleHandler retryAbleHandler = InstanceFactory
                .getInstance()
                .threadSafe(retryAbleBean.retryAble().value());


        // 3. 构建执行上下文
        RetryContext retryContext = retryAbleHandler.build(retryAbleBean.annotation(), callable);
        return Retryer.newInstance().retryCall(retryContext);
    }

    /**
     * 重试调用
     * @param retryAbleBean 重试调用对象
     * @param callable 待重试方法
     * @return 执行结果
     */
    public Object retryCall(final RetryAbleBean retryAbleBean,
                            final Callable callable) {
        final RetryAbleHandler retryAbleHandler = InstanceFactory
                .getInstance()
                .threadSafe(retryAbleBean.retryAble().value());


        // 3. 构建执行上下文
        RetryContext retryContext = retryAbleHandler.build(retryAbleBean.annotation(), callable);
        return Retryer.newInstance().retryCall(retryContext);
    }

    /**
     * 为了处理简单，只匹配第一个符合条件的重试注解
     * @param method 方法体
     * @return optional
     */
    public Optional<RetryAbleBean> retryAbleAnnotation(final Method method) {
        Annotation[] annotations = method.getAnnotations();
        if(ArrayUtil.isEmpty(annotations)) {
            return Optional.empty();
        }

        for(Annotation annotation : annotations) {
            RetryAble retryAble = annotation.annotationType().getAnnotation(RetryAble.class);
            if (ObjectUtil.isNotNull(retryAble)) {
                RetryAbleBean bean = new RetryAbleBean();
                bean.retryAble(retryAble).annotation(annotation);
                return Optional.of(bean);
            }
        }

        return Optional.empty();
    }

    /**
     * 构建 callable
     * @param proxy 代理
     * @param method 方法
     * @param args 参数
     * @return callable 对象
     */
    private Callable buildCallable(final Object proxy, final Method method, final Object[] args) {
        return new Callable() {
            @Override
            public Object call() throws Exception {
                return method.invoke(proxy, args);
            }
        };
    }

}
