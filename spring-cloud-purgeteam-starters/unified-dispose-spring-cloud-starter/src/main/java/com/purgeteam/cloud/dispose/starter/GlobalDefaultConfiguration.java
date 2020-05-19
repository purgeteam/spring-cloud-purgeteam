package com.purgeteam.cloud.dispose.starter;

import com.purgeteam.cloud.dispose.starter.advice.CommonResponseDataAdvice;
import com.purgeteam.cloud.dispose.starter.exception.GlobalDefaultExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * {@link GlobalDefaultExceptionHandler} {@link CommonResponseDataAdvice} bean配置加载
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfiguration {

    @Bean
    public GlobalDefaultExceptionHandler globalDefaultExceptionHandler() {
        return new GlobalDefaultExceptionHandler();
    }

    @Bean
    public CommonResponseDataAdvice commonResponseDataAdvice(GlobalDefaultProperties globalDefaultProperties) {
        return new CommonResponseDataAdvice(globalDefaultProperties);
    }

}
