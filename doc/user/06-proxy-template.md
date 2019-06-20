# sisyphus 代理模板

## 目的

为了便于用户更加方便地使用注解，同时又不依赖 spring。

提供基于代码模式+字节码增强实现的方式。

# 使用案例

## maven 引入

引入注解相关模块。

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>sisyphus-annotation</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 定义测试方法

以下测试代码可以参考 [spring-test]() 模块。

- MenuServiceImpl.java

```java
public class MenuServiceImpl {

    public void queryMenu(long id) {
        System.out.println("查询菜单...");
        throw new RuntimeException();
    }

    @Retry
    public void queryMenuRetry(long id) {
        System.out.println("查询菜单...");
        throw new RuntimeException();
    }

}
```

## 测试

使用 RetryTemplate 进行测试

### 无重试注解的方法

```java
@Test(expected = RuntimeException.class)
public void templateTest() {
    MenuServiceImpl menuService = RetryTemplate.getProxyObject(new MenuServiceImpl());
    menuService.queryMenu(1);
}
```

- 日志信息

```
查询菜单...
```

只请求了一次。

### 有注解的方法

```java
@Test(expected = RuntimeException.class)
public void templateRetryTest() {
    MenuServiceImpl menuService = RetryTemplate.getProxyObject(new MenuServiceImpl());
    menuService.queryMenuRetry(1);
}
```

- 日志信息

```
查询菜单...
查询菜单...
查询菜单...
```

# 其他

当然还有更多的配置，可以自行尝试。

如果你想结合 spring 使用注解，请继续往下看。