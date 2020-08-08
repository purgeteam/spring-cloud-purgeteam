package com.purgeteam.cloud.swagger.starter.gateway;

import com.purgeteam.cloud.swagger.starter.SwaggerRoute;
import com.purgeteam.cloud.swagger.starter.utli.SwaggerUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * {@link SwaggerResourcesProvider} Swagger 服务列表获取 gateway
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Primary
public class GatewaySwaggerDocumentationConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;
    private final SwaggerUtils swaggerUtils;

    public GatewaySwaggerDocumentationConfig(RouteLocator routeLocator, SwaggerUtils swaggerUtils,
                                             GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
        this.swaggerUtils = swaggerUtils;
    }

    @Override
    public List<SwaggerResource> get() {
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        Set<SwaggerRoute> swaggerRoutes = new HashSet<>();
        gatewayProperties.getRoutes().stream()
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition ->
                                swaggerRoutes.add(
                                        new SwaggerRoute(
                                                routeDefinition.getId(),
                                                routeDefinition.getId(),
                                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        )
                                )
                        )
                );

        return swaggerUtils.getSwaggerResourceListSorting(swaggerRoutes);
    }

}
