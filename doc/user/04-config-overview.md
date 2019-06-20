# 配置概览

为了满足更加方便的配置，Retryer 类提供了许多可以配置的信息。

## 默认配置

```java
/**
 * 默认配置测试
 */
public void defaultConfigTest() {
    Retryer.<String>newInstance()
            .condition(RetryConditions.hasExceptionCause())
            .retryWaitContext(RetryWaiter.<String>retryWait(NoRetryWait.class).context())
            .maxAttempt(3)
            .listen(RetryListens.noListen())
            .recover(Recovers.noRecover())
            .callable(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("called...");
                    throw new RuntimeException();
                }
            }).retryCall();
}
```

和下面的代码是等价的：

```java
public void helloTest() {
    Retryer.<String>newInstance()
            .callable(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("called...");
                    throw new RuntimeException();
                }
            }).retryCall();
}
```

# 方法说明

## condition

重试触发的条件，可以指定多个条件。

默认为抛出异常。

## retryWaitContext

重试等待的策略，可以指定多个。

默认为不做任何等待。 

## maxAttempt

指定最大重试次数，包括第一次执行。

默认值：3 次。

## listen

指定重试的监听实现，默认为不做监听。

## recover

当重试完成之后，依然满足重试条件，则可以指定恢复的策略。

默认不做恢复。

## callable

待重试执行的方法。

## retryCall

触发重试执行。

# 接口的详细介绍

## 接口及其实现

所有的接口，都可以直接查看对应的子类实例。

## 用户自定义

基于替换的灵活性，用户可以实现接口，定义更符合自己业务的实现。

