# sisyphus springboot 整合

## 目的

类似于 spring-retry 框架，如果你使用 springboot 框架，那么整合本项目将会非常简单。

注解的方式和过程式编程，二者尽可能的保持一致性，你想从一种方式变为另一种也比较简单。

想从  spring-retry 切换到本框架也很方便。

整体与 spring 整合类似，而且更加简单。

# 使用示例

## maven 引入

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>sisyphus-springboot-starter</artifactId>
    <version>${project.version}</version>
</dependency>
```

会默认引入 springboot 整合相关的依赖。

## 业务代码

你可以参考 sisyphus-test springboot 测试模块。

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

## 测试代码

- SisyphusApplicationTest.java

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SisyphusApplication.class)
public class SisyphusApplicationTest {

    @Autowired
    private SpringService springService;

    @Test(expected = RuntimeException.class)
    public void queryTest() {
        springService.query();
    }

}
```

其中 SisyphusApplication.java 代码如下：

就是最基本的 springboot 启动入口。

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.github.houbb.sisyphus.test.service")
public class SisyphusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisyphusApplication.class, args);
    }

}
```

- 日志信息

```
spring service query...
spring service query...
spring service query...
```

