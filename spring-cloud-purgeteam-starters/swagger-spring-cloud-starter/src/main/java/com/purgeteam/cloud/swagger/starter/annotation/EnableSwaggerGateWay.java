package com.purgeteam.cloud.swagger.starter.annotation;

import com.purgeteam.cloud.swagger.starter.SwaggerProperties;
import com.purgeteam.cloud.swagger.starter.gateway.GatewaySwaggerDocumentationConfig;
import com.purgeteam.cloud.swagger.starter.gateway.GatewaySwaggerHandler;
import com.purgeteam.cloud.swagger.starter.utli.SwaggerUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * {@link GatewaySwaggerDocumentationConfig} {@link GatewaySwaggerHandler} 加载控制
 * 因为Swagger暂不支持webflux项目，所以Gateway里不能配置SwaggerConfig，也就是说Gateway无法提供自身API。
 * 配置SwaggerProvider，获取Api-doc，即SwaggerResources
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({GatewaySwaggerDocumentationConfig.class, GatewaySwaggerHandler.class, SwaggerUtils.class})
@EnableConfigurationProperties(SwaggerProperties.class)
public @interface EnableSwaggerGateWay {

}
