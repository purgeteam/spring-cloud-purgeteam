# spirng-cloud-purgeteam

![SpringBoot](https://img.shields.io/badge/SpringBoot-2.1.8RELEASE-green.svg)
![SpringCloud](https://img.shields.io/badge/SpringCloud-Greenwich.RELEASE-green.svg)
![JAVA](https://img.shields.io/badge/JAVA-1.8+-green.svg)
![Maven](https://img.shields.io/badge/Maven-3.0+-green.svg)
![badge](https://img.shields.io/badge/buil-passing-green.svg)

## 简介:
简化开发共用代码过程，包含以下组件：

- [全局处理(异常+统一)](https://www.purgeteam.com/)

todo: 其他模块整理集成中。。。

## 功能使用

**ps:** 实际version版本请使用最新版
**最新版本:** [![Maven Central](https://img.shields.io/maven-central/v/com.purgeteam.cloud/spirng-cloud-purgeteam.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.purgeteam.cloud%20AND%20a:spirng-cloud-purgeteam)

[点击查看最新新版本](https://search.maven.org/search?q=g:com.purgeteam.cloud%20AND%20a:spirng-cloud-purgeteam)

> 添加依赖

为了方便管理统一版本，在项目 `pom` `<dependencyManagement>` 添加默认 `purgeteam` 组件版本管理。
```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.purgeteam.cloud</gro upId>
            <artifactId>spirng-cloud-purgeteam</artifactId>
            <version>0.3.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```