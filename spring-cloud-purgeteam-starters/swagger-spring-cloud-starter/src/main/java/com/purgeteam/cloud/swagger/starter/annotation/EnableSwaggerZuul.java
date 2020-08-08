package com.purgeteam.cloud.swagger.starter.annotation;

import com.purgeteam.cloud.swagger.starter.utli.SwaggerUtils;
import com.purgeteam.cloud.swagger.starter.zuul.ZuulSwaggerDocumentationConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * zuul 集成 swagger
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ZuulSwaggerDocumentationConfig.class, SwaggerUtils.class})
@EnableSwaggerPlugins
public @interface EnableSwaggerZuul {

}
