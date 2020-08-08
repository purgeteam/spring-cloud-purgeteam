package com.purgeteam.cloud.swagger.starter.annotation;

import com.purgeteam.cloud.swagger.starter.SwaggerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@link SwaggerConfig} 加载控制
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SwaggerConfig.class)
@EnableSwagger2
public @interface EnableSwaggerPlugins {

}
