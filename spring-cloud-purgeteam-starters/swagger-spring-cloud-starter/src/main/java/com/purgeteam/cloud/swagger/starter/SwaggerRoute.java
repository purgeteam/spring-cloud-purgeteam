package com.purgeteam.cloud.swagger.starter;

import springfox.documentation.swagger.web.SwaggerResource;

/**
 * 构建 {@link SwaggerResource} 基础对象
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class SwaggerRoute {

    /**
     * 如: giant-demo
     */
    private String id;

    /**
     * 如: /giant-demo/**
     */
    private String fullPath;

    /**
     * 如: /**
     */
    private String path;

    /**
     * 如: giant-demo-service
     */
    private String location;

    /**
     * 如: /giant-demo
     */
    private String prefix;

    public SwaggerRoute(org.springframework.cloud.netflix.zuul.filters.Route route) {
        this.id = route.getId();
        this.path = route.getPath();
        this.location = route.getLocation();
        this.prefix = route.getPrefix();
        this.fullPath = route.getFullPath();
    }

    public SwaggerRoute(org.springframework.cloud.gateway.route.Route route) {

    }

    public SwaggerRoute(String id, String location, String fullPath) {
        this.id = id;
        this.location = location;
        this.fullPath = fullPath;
    }

    @Override
    public boolean equals(Object obj) {
        SwaggerRoute swaggerRoute = (SwaggerRoute) obj;
        return this.getLocation().equals(swaggerRoute.getLocation());
    }

    @Override
    public int hashCode() {
        String in = this.getLocation();
        return in.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
