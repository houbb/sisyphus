# 为什么选择 sisyphus

作为开发者，我们一般都会选择比较著名的框架。

比如 guava-retrying spring-retry。

或者干脆自己写一个。

# 为什么不是 guava-retrying/spring-retry

[java retry](https://houbb.github.io/2018/08/08/retry) 这篇文章中我列举了常见的实现方式
以及上述的两种框架，也讲述了其中的不足。

## guava-retrying 优缺点

### 优点

- 使用灵活

- fluent 优雅写法

- 提供足够多的实现

### 缺点

- 没有默认基于注解的实现

- 重试策略设计并不友好

## spring-retry

### 优点

- 使用简单

### 缺点

- 重试条件单一

- 重试等待策略单一

- 无法自定义注解

# 为什么不自己写一个

## 个人感受

我作为一名开发，平时说实在的，看到重试。

我肯定会偷懒写一个 for 循环，重试几次就结束了。

因为时间不允许。

如果你更勤快一点，就可以选择 spring-retry/guava-retrying。如果你熟悉他们的优缺点的话。

## 如果你渴望创造

sisyphus 所有的实现都是基于接口的。

你完全可以实现自己的实现，所有的东西基本完全可以被替换。

当然一些常见的策略实现，项目的基本框架都有详尽的注释，当做参考也可以有一点帮助。

# sisyphus 做的更多的事情

## netty 的灵感

参考了 netty 的设计，保证接口实现的一致性。

而且 sisyphus 还做了更多，还保证了接口和注解之间的一致性。

使用引导类，保证使用时的便利性，后期拓展的灵活性。

## hibernate-validator

hibernate-validator 的作者是我知道为数不多的对于 java 注解应用很棒的开发者。（虽然所知甚少）

自定义注解就是从这个框架中学来的。

## 与 spring 为伍

spring 基本与我们的代码形影不离，所以你可以很简单的结合 spring.

就像你使用 spring-retry 一样。



