package com.purgeteam.cloud.swagger.starter.utli;

import com.purgeteam.cloud.swagger.starter.SwaggerProperties;
import com.purgeteam.cloud.swagger.starter.SwaggerRoute;
import com.purgeteam.cloud.swagger.starter.constant.SwaggerConstant;

import java.net.InetAddress;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * {@link SwaggerResource} 处理
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class SwaggerUtils implements ApplicationListener<WebServerInitializedEvent> {

    private static final Logger log = LoggerFactory.getLogger(SwaggerUtils.class);

    private WebServerInitializedEvent event;
    private RestTemplate restTemplate;
    private SwaggerProperties swaggerProperties;

    public SwaggerUtils(SwaggerProperties properties) {
        this.swaggerProperties = properties;
        this.restTemplate = new RestTemplate();
    }

    /**
     * 请求微服务 {@link SwaggerResource} 集合 包含是否排序
     *
     * @param routes Route set
     * @return SwaggerResource list
     */
    public List<SwaggerResource> getSwaggerResourceListSorting(Set<? extends SwaggerRoute> routes) {
        // 是否排序
        if (swaggerProperties.getIsOptimizeLocation()) {
            List<SwaggerResource> resourceList = getSwaggerResourceList(routes);
            Collections.sort(resourceList);
            return resourceList;
        }
        return getSwaggerResourceList(routes);
    }

    /**
     * 请求微服务 {@link SwaggerResource} 集合
     *
     * @param routes Route set
     * @return SwaggerResource list
     */
    public List<SwaggerResource> getSwaggerResourceList(Set<? extends SwaggerRoute> routes) {
        long startTime = System.currentTimeMillis();

        List<FutureTask<SwaggerResource>> futureTasks = routes.stream()
                .map(route -> new FutureTask<>(() -> apply(route)))
                .collect(Collectors.toList());
        List<Thread> threads = futureTasks.stream().map(Thread::new).collect(Collectors.toList());
        threads.forEach(Thread::start);

        List<SwaggerResource> swaggerResources = futureTasks.stream()
                .map(swaggerResourceFutureTask -> {
                    SwaggerResource swaggerResource = null;
                    try {
                        swaggerResource = swaggerResourceFutureTask.get();
                    } catch (Exception e) {
                        log.warn("线程执行异常");
                    }
                    return swaggerResource;
                }).collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        log.debug("所有服务获取 耗时为{}毫秒", (endTime - startTime));

        return swaggerResources;
    }

    /**
     * 请求微服务 {@link SwaggerResource}
     *
     * @param route Route
     * @return SwaggerResource
     */
    public SwaggerResource apply(SwaggerRoute route) {
        long startTime = System.currentTimeMillis();
        String name = route.getId();
        try {
            int port = event.getWebServer().getPort();
            InetAddress address = InetAddress.getLocalHost();
            String url = "http://" + address.getHostAddress() + ":" + port;
            LinkedHashMap forObject = restTemplate
                    .getForObject(url + "/" + route.getLocation() + "/" + SwaggerConstant.LOCATION,
                            LinkedHashMap.class);
            if (forObject != null) {
                LinkedHashMap info = (LinkedHashMap) forObject.get("info");
                if (info != null) {
                    Object title = info.get("title");
                    if (title != null) {
                        name = "health > " + title + "(" + name + ")";
                    }
                }
            }
        } catch (Exception e) {
            log.warn("{} 服务api-docs获取失败.", route.getLocation());
            name = "sick > " + name + "(已下线)";
        }
        SwaggerResource swaggerResource = SwaggerUtils.swaggerResource(name,
                route.getFullPath().replace("**", SwaggerConstant.LOCATION),
                swaggerProperties.getVersion());
        long endTime = System.currentTimeMillis();
        log.debug("{}: {} 服务获取 耗时为{}毫秒.", Thread.currentThread().getId(), route.getLocation(),
                (endTime - startTime));

        return swaggerResource;
    }

    /**
     * 构建 {@link SwaggerResource}
     *
     * @param name     服务名称
     * @param location 地址
     * @param version  版本
     * @return SwaggerResource
     */
    public static SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.event = event;
    }
}
