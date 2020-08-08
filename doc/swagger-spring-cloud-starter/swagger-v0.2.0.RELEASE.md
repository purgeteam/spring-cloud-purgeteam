# swagger-deepblueai-starter
## 功能点:
- 集成`swagger`前端接口文档
- `Swagger` 整合 `zuul` 智能列表
- 无缝集成 `knife4j` 前端文档 支持 `v0.1.2RELEASE` `zuul` 智能列表

## 简介

在原有 `swagger` 功能之上集成 `knife4j`。

`knife4j` 是 `springfox-swagger` 的增强UI实现，为Java开发者在使用Swagger的时候，能拥有一份简洁、强大的接口文档体验。

该UI增强包主要包括两大核心功能：**文档说明** 和 **在线调试**

**文档说明：** 根据Swagger的规范说明，详细列出接口文档的说明，包括接口地址、类型、请求示例、请求参数、响应示例、响应参数、响应码等信息，使用swagger-bootstrap-ui能根据该文档说明，对该接口的使用情况一目了然。

**在线调试：** 提供在线接口联调的强大功能，自动解析当前接口参数,同时包含表单验证，调用参数可返回接口响应内容、headers、Curl请求命令实例、响应时间、响应状态码等信息，帮助开发者在线调试，而不必通过其他测试工具测试接口是否正确,简介、强大。

## 功能使用

### 添加依赖

```
<dependency>
  <groupId>com.purgeteam</groupId>
  <artifactId>swagger-spring-boot-starter<factId>
  <version>0.2.0.RELEASE</version>
</dependency>
```

### UI访问

访问地址： `http://ip:端口/doc.html`

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-home.png)

可以访问基本ok。

### 全局token

新增 `Authorize` 全局 `token`

默认参数设置为了 `Authorization` 储存在 `header`, 如和自己的参数不一致请在 `通用参数配置` 设置。

![knife4j-token.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-token.png)

### 通用参数配置

通用参数配置是一个比较常用的功能，如 携带 `token` 访问接口。

和 `postman` 功能类似，解决 `swagger` 缺陷。 

**开启功能**

**路径：**  `文档管理/个性化设置`

- 启用Knife4j提供的增强功能
- 开启动态请求参数

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-function.png)

**添加参数**

**路径：**  `文档管理/全局参数设置`

添加 `oauth2 token` 值已自己登陆token 前缀记得添加 `Bearer `。

```
参数名称: Authorization
参数值: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODI4MjYyNTIsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9KSUFPU0UxIiwiUk9MRV9NRU1CRVJTIl0sImp0aSI6IjA3YWZjMDVhLWU3NjYtNDMxOC1iZGRmLWJkMWU4NTExOWU5MiIsImNsaWVudF9pZCI6InNzby1hdXRoLXNlcnZlciIsInNjb3BlIjpbInNlcnZlciJdfQ.LFMcZTXb0g4xTzRo8kVAwBbXe12-XRsYWJkHFBRCbWg
```

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-parameter.png)

**访问接口**

需要登陆的接口 在请求头里会默认都添加有 `Authorization`。

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-user.png)

### 离线文档

Knife4j提供导出4种格式的离线文档(Html\Markdown\Word\Pdf)

**路径:** 文档管理/离线文档

![image.png](https://raw.githubusercontent.com/purgeyao/purgeyao.github.io/master/img/blog/other/knife4j/knife4j-document.png)