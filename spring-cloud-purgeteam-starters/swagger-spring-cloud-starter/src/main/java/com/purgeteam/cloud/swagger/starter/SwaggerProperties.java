package com.purgeteam.cloud.swagger.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 基础配置
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@ConfigurationProperties(SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "swagger";

    /**
     * 文档扫描包路径
     */
    private String basePackage = "";

    /**
     * title 如: 用户模块系统接口详情
     */
    private String title = "平台系统接口详情";

    /**
     * 服务文件介绍
     */
    private String description = "在线文档";

    /**
     * 服务条款网址
     */
    private String termsOfServiceUrl = "https://www.purgeteam.com/";

    /**
     * 版本
     */
    private String version = "V1.0";

    /**
     * 是否优化 网关 select a spec 列表
     */
    private Boolean isOptimizeLocation = true;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getIsOptimizeLocation() {
        return isOptimizeLocation;
    }

    public void setIsOptimizeLocation(Boolean isOptimizeLocation) {
        this.isOptimizeLocation = isOptimizeLocation;
    }

}