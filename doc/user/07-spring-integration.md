# sisyphus spring 整合

## 目的

类似于 spring-retry 框架，如果你使用 spring 框架，那么整合本项目将会非常简单。

注解的方式和过程式编程，二者尽可能的保持一致性，你想从一种方式变为另一种也比较简单。

想从  spring-retry 切换到本框架也很方便。


# 使用示例

## maven 引入

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>sisyphus-spring</artifactId>
    <version>${project.version}</version>
</dependency>
```

会默认引入 spring 以及 AOP 相关 jar。

## 业务代码

你可以参考 sisyphus-test 模块。

下面模拟非常常见的一些业务方法。

使用 `@Retry` 标识方法需要进行重试。

- SpringService.java

```java
public interface SpringService {

    /**
     * 查询示例代码
     * @return 结果
     */
    String query();

}
```

- SpringServiceImpl.java

```java
import com.github.houbb.sisyphus.annotation.annotation.Retry;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.springframework.stereotype.Service;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
@Service
public class SpringServiceImpl implements SpringService {

    @Override
    @Retry
    public String query() {
        System.out.println("spring service query...");
        throw new RuntimeException();
    }

}
```

## 开启重试

基于注解直接如下配置即可。

使用 `@EnableRetry` 标识需要开启重试。

```java
@Configurable
@ComponentScan(basePackages = "com.github.houbb.sisyphus.test.service")
@EnableRetry
public class SpringConfig {
}
```

## 测试代码

```java
import com.github.houbb.sisyphus.test.config.SpringConfig;
import com.github.houbb.sisyphus.test.service.SpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringServiceTest {

    @Autowired
    private SpringService springService;

    @Test(expected = RuntimeException.class)
    public void queryTest() {
        springService.query();
    }

}
```

- 日志信息

```
spring service query...
spring service query...
spring service query...
```

