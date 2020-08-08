package com.purgeteam.cloud.swagger.example.a;

import com.purgeteam.cloud.swagger.starter.annotation.EnableSwaggerPlugins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@EnableSwaggerPlugins
@SpringBootApplication
public class SwaggerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerSpringBootApplication.class, args);
    }

}
