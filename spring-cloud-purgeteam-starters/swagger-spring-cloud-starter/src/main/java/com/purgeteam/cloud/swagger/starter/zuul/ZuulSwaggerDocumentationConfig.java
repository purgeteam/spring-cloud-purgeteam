package com.purgeteam.cloud.swagger.starter.zuul;

import com.purgeteam.cloud.swagger.starter.SwaggerRoute;
import com.purgeteam.cloud.swagger.starter.utli.SwaggerUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * {@link SwaggerResourcesProvider} Swagger 服务列表获取
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Primary
public class ZuulSwaggerDocumentationConfig implements SwaggerResourcesProvider {

    private static final Logger log = LoggerFactory.getLogger(ZuulSwaggerDocumentationConfig.class);

    private final RouteLocator routeLocator;
    private SwaggerUtils swaggerUtils;

    public ZuulSwaggerDocumentationConfig(RouteLocator routeLocator, SwaggerUtils swaggerUtils) {
        this.routeLocator = routeLocator;
        this.swaggerUtils = swaggerUtils;
    }

    @Override
    public List<SwaggerResource> get() {

        List<Route> routes = routeLocator.getRoutes();
        Set<SwaggerRoute> swaggerRoutes = routes.stream()
                .map(SwaggerRoute::new).collect(Collectors.toSet());

        return swaggerUtils.getSwaggerResourceListSorting(swaggerRoutes);
    }
}
