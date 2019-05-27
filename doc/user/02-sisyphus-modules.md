# 模块划分

sisyphus 在模块划分的时候考虑到使用者的方便，主要有几个模块：

# sisyphus-api

接口定义模块，是最基础的部分。

会被 sisyphus-core 默认依赖。

一般不需要引入，如果你想根据它实现自己的重试框架，不妨一试。

# sisyphus-core

对于 sisyphus-api 模块的默认实现。

并且添加易于使用的 Fluent 引导类，可以很方便的写出声明式的重试代码。

# sisyphus-annotation

sisyphus 的注解实现模块。

（1）基于字节码实现的代理重试，可以不依赖 spring。平时使用也更加灵活

（2）允许自定义注解及其实现。使用者可以编写属于自己的重试注解。

# sisyphus-spring

spring 做为 java 开发的引导者。自然是要支持的。

你可以和使用 spring-retry 一样方便的使用 sisyphus-spring。

# 模块间的依赖关系

```
sisyphus-api
sisyphus-core
sisyphus-annotation
sisyphus-spring
sisyphus-test
```

sisyphus-api 是基础的，灵活性最高。

sisyphus-spring 是最简单易用的，灵活性相对较差。

sisyphus-test 仅仅用作测试，不用外部引入。


