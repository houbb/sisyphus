# 快速开始

## 需要

jdk1.7+

maven 3.x+

## maven 引入

sisyphus 使用 maven 管理 jar，

```xml
<plugin>
    <groupId>com.github.houbb</groupId>
    <artifactId>sisyphus-core</artifactId>
    <version>0.0.6</version>
</plugin>
```

## 编码

作为入门案例，我们首先介绍些简单灵活的声明式编程

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

### 代码简介

`Retryer.<String>newInstance()` 创建引导类的实例，String 是 callable 也就是待重试方法的返回值类型。

`callable()` 指定待重试的方法实现。

`retryCall()` 触发重试调用。

### 日志信息

```
called...
called...
called...
```

以及一些异常信息。

## 等价配置

上面的配置其实有很多默认值，如下：

```java
/**
 * 默认配置测试
 */
@Test(expected = RuntimeException.class)
public void defaultConfigTest() {
    Retryer.<String>newInstance()
            .maxAttempt(3)
            .listen(RetryListens.noListen())
            .recover(Recovers.noRecover())
            .condition(RetryConditions.hasExceptionCause())
            .retryWaitContext(RetryWaiter.<String>retryWait(NoRetryWait.class).context())
            .callable(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("called...");
                    throw new RuntimeException();
                }
            }).retryCall();
}
```

这些默认值都是可以配置的。

# 更多的学习

当然上面的案例，有很多默认值。

比如什么时候触发重试？

重试几次？

多久触发一次重试？

这些都会在下面的章节进行详细讲解。



