# swagger-spring-boot

[![Maven Central](https://img.shields.io/maven-central/v/com.purgeteam/swagger-spring-boot-starter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.purgeteam%20AND%20a:swagger-spring-boot-starter)
![License](https://img.shields.io/badge/SpringBoot-2.1.7RELEASE-green.svg)
![License](https://img.shields.io/badge/JAVA-1.8+-green.svg)
![License](https://img.shields.io/badge/maven-3.0+-green.svg)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 简介

可能大家都有用过`swagger`,可以通过`ui`页面显示接口信息，快速和前端进行联调。

现在基本都是多模块微服务化，每个服务都有这样的ui页面也是很不方便，`swagger` 也可以聚合在网关页面。

有开发过微服务的小伙伴应该体验过。当微服务模块多的情况下，每个模块都需要配置这样的一个类进行加载 `swagger` 。造成每个模块都存在大致一样的 `SwaggerConfig` ,极端的情况下，有些朋友复制其他模块的 `SwaggerConfig` 进行改造之后，发现仍然加载不出 `swagger` 的情况，造成明明是复制的，为何还加载不出，排查此bug极其费时间。

在此之上，可以构建出一个 `swagger-starter` 模块，只需要引用一个 `jar` ，加载一些特殊的配置，就可以快速地使用到 `swagger` 的部分功能了。

## 功能使用

### 添加依赖

**ps:** 实际version版本请使用最新版
**最新版本:** [![Maven Central](https://img.shields.io/maven-central/v/com.purgeteam/swagger-spring-boot-starter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.purgeteam%20AND%20a:swagger-spring-boot-starter)

[点击查看最新新版本](https://search.maven.org/search?q=g:com.purgeteam%20AND%20a:swagger-spring-boot-starter)

```
<dependency>
  <groupId>com.purgeteam</groupId>
  <artifactId>swagger-spring-boot-starter<factId>
  <version>0.1.2.RELEASE</version>
</dependency>
```

### 配置swagger.properties文件

在自己项目模块的`resources`目录下 创建`swagger.properties`配置

```
swagger.basePackage="swagger扫描项目包路径"
swagger.title="swagger网页显示标题"
swagger.description="swagger网页显示介绍"
```

### `@EnableSwaggerPlugins`注解。

```
@EnableSwaggerPlugins
@SpringBootApplication
public class FrontDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(FrontDemoApplication.class, args);
  }

}
```

访问`http://ip:端口/swagger-ui.html`检查swagger-ui是否正常。

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/2019-12-27/00.png)

## Zuul网关集成

做完上面步骤一个单体服务已经完成了 `swagger` 的配置。

集成到 `zuul` 网关上还需要配置其他的集成配置。

不过使用 `swagger-spring-boot-starter` 之后，流程变得很轻松。

只需要添加下面 `@EnableSwaggerZuul` 注解即可完成集成动作。

```
@EnableSwaggerZuul
@SpringBootApplication
public class ZuulApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }

}
```

访问`http://ip:端口/swagger-ui.html`检查swagger-ui是否正常。

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/2019-12-27/00.png)

**状态支持**

在 `Select a spec` 选择框里可以选择网关下的微服务列表进行聚合展示。

这里也支持了服务状态显示。

```
health > "用户服务"(user)
health > "认证服务"(auth)
sick > front-demo(已下线)
sick > giant-demo(已下线)
```

这里的 `用户服务` `认证服务` 名称是根据相应服务的 `swagger.properties` 文件属性名 `swagger.title` 获取。

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/2019-12-27/01.png)

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/2019-12-27/02.png)

## 总结

简单的`starter`代码编写可以减少新模块的复杂性，只需要简单的配置就可以使用相应的特性，减少复制代码不必要的错误。

> 示例代码地址:[swagger-spring-boot](https://github.com/purgeteam/swagger-spring-boot)

> 作者GitHub:
[Purgeyao](https://github.com/purgeyao) 欢迎关注

> qq交流群: `812321371` 微信交流群: `MercyYao`

> 微信公众号:

![微信公众号二维码](https://purgeyao.github.io/img/about-my-mp-8cm.jpg)
