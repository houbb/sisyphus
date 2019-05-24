package com.github.houbb.sisyphus.spring.aop;

import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
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

    @Pointcut("@annotation(com.github.houbb.sisyphus.annotation.annotation.Retry)")
    public void myPointcut() {
    }

    @Around("myPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = getCurrentMethod(point);
        Retry retry = method.getAnnotation(Retry.class);
        final Callable callable = buildCallable(point);
        final RetryAbleBean retryAbleBean = buildRetryAbleBean(retry);
        return InstanceFactory.getInstance().singleton(RetryMethodHandler.class)
                .retryCall(retryAbleBean, callable);
    }

    /**
     * 构建重试对象
     * @param retryable 重试对象
     * @return 对象本身
     */
    private RetryAbleBean buildRetryAbleBean(final Retry retryable) {
        RetryAbleBean retryAbleBean = new RetryAbleBean();
        retryAbleBean.annotation(retryable);
        retryAbleBean.retryAble(retryable.annotationType().getAnnotation(RetryAble.class));
        return retryAbleBean;
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
