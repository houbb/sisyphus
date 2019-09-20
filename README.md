# Sisyphus 

支持过程式编程和注解编程的 java 重试框架。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sisyphus/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sisyphus)
[![Build Status](https://www.travis-ci.org/houbb/sisyphus.svg?branch=master)](https://www.travis-ci.org/houbb/sisyphus?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/sisyphus/badge.svg?branch=master)](https://coveralls.io/github/houbb/sisyphus?branch=master)

![sisyphus.jpg](sisyphus.jpg)

## 特性

- 支持 fluent 过程式编程

- 基于字节码的代理重试

- 基于注解的重试，允许自定义注解

- 无缝接入 spring

- 接口与注解的统一

- 解决 spring-retry 与 guava-retrying 中的不足之处

## 设计目的

综合了 spring-retry 和 gauva-retrying 的优势。

调整一些特性，使其更利于实际使用。

采用 Netty 类似的接口思想，保证接口的一致性，和替换的灵活性。

借鉴 Hibernate-Validator 的设计，允许用户自定义注解。

> [spring-retry 与 guava-retrying 中的不足之处](https://www.jianshu.com/p/2e3cfc509d56)

## 更新记录

> [更新记录](doc/CHANGE_LOG.md)

## 开源地址

> [sisyphus](https://github.com/houbb/sisyphus)

# 快速开始

## 引入

```xml
<plugin>
    <groupId>com.github.houbb</groupId>
    <artifactId>sisyphus-core</artifactId>
    <version>0.0.6</version>
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


# 拓展阅读

[00-sisyphus 是什么](doc/user/00-what-is-sisyphus.md)

[01-为什么选择 sisyphus](doc/user/01-why-sisyphus.md)

[02-sisyphus 模块简介](doc/user/02-sisyphus-modules.md)

[03-sisyphus 快速开始](doc/user/03-quick-start.md)

[04-sisyphus 配置概览](doc/user/04-config-overview.md)

[05-sisyphus 注解简介](doc/user/05-annotation.md)

[06-sisyphus 代理模板](doc/user/06-proxy-template.md)

[07-sisyphus spring 整合](doc/user07-spring-integration.md)

# 新特性预定

## 入参

重试上下文添加入参信息

## 配置优化

提供更加优异的配置体验
