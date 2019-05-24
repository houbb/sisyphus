package com.github.houbb.sisyphus.spring.aop;

import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.annotation.annotation.metadata.RetryAble;
import com.github.houbb.sisyphus.annotation.handler.method.RetryMethodHandler;
import com.github.houbb.sisyphus.annotation.model.RetryAbleBean;
import com.github.houbb.sisyphus.api.exception.RetryException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 重试 aop
 * @author binbin.hou
 * @since 0.0.4
 */
@Aspect
@Component
public class RetryAop {

    /**
     * 扫描所有的共有方法
     */
    @Pointcut("execution(public * *(..))")
    public void myPointcut() {
    }

    /**
     * 执行核心方法
     * 1. 判断是否拥有 {@link RetryAble} 标识的注解。
     * 2. 没有则正常执行。
     * @param point 切点
     * @return 结果
     * @throws Throwable 异常信息
     */
    @Around("myPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        final Method method = getCurrentMethod(point);
        final RetryMethodHandler retryMethodHandler = InstanceFactory.getInstance()
                .singleton(RetryMethodHandler.class);

        //1. 判断注解信息
        Optional<RetryAbleBean> retryAbleAnnotationOpt = retryMethodHandler.retryAbleAnnotation(method);
        if(!retryAbleAnnotationOpt.isPresent()) {
            return point.proceed();
        }

        //2. 重试执行
        final Callable callable = buildCallable(point);
        final RetryAbleBean retryAbleBean = retryAbleAnnotationOpt.get();
        return retryMethodHandler.retryCall(retryAbleBean, callable);
    }

    /**
     * 获取当前方法信息
     *
     * @param point 切点
     * @return 方法
     */
    private Method getCurrentMethod(ProceedingJoinPoint point) {
        try {
            Signature sig = point.getSignature();
            MethodSignature msig = (MethodSignature) sig;
            Object target = point.getTarget();
            return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException e) {
            throw new RetryException(e);
        }
    }

    /**
     * 构建 callable
     *
     * @param point 切点
     * @return callable 对象
     */
    private Callable buildCallable(final ProceedingJoinPoint point) {
        return new Callable() {
            @Override
            public Object call() {
                try {
                    return point.proceed();
                } catch (Throwable throwable) {
                    throw new RetryException(throwable);
                }
            }
        };
    }

}
