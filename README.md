# sisyphus 

支持过程式编程和注解编程的 java 重试框架。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sisyphus/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sisyphus)
[![Build Status](https://www.travis-ci.org/houbb/sisyphus.svg?branch=master)](https://www.travis-ci.org/houbb/sisyphus?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/sisyphus/badge.svg?branch=master)](https://coveralls.io/github/houbb/sisyphus?branch=master)

## 特性

- 支持 fluent 过程式编程

- 基于注解的重试策略

- 整合 spring

## 设计目的

综合了 spring-retry 和 gauva-retrying 的优势。

调整一些特性，使其更利于实际使用。

采用 Netty 类似的接口思想，保证接口的一致性，和替换的灵活性。

## 更新记录

> [更新记录](doc/CHANGE_LOG.md)

# 快速开始

## 引入

```xml
<plugin>
    <groupId>com.github.houbb</groupId>
    <artifactId>sisyphus-core</artifactId>
    <version>0.0.2</version>
</plugin>
```

## 入门代码

详情参见 [RetryerTest]()

```java
public void helloTest() {
    Retryer.<String>newInstance()
            .retry(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("called...");
                    throw new RuntimeException();
                }
            });
}
```

## 代码分析

- retry

指定一个 callable 的实现。

我们打印一条日志，并且模拟一个程序异常。

## 日志信息

日志信息

```
called...
called...
called...
```

和一些其他异常信息。

重试触发的条件，默认是程序发生了异常

这里的重试间隔默认为没有时间间隔，一共尝试3次。（包括第一次程序本身执行）
