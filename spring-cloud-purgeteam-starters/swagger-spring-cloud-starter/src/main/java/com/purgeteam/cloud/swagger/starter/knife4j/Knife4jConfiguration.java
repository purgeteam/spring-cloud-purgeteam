package com.purgeteam.cloud.swagger.starter.knife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link DocketPostProcessor} 加载
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Configuration
public class Knife4jConfiguration {

    @Bean
    public DocketPostProcessor docketPostProcessor() {
        return new DocketPostProcessor();
    }
}
