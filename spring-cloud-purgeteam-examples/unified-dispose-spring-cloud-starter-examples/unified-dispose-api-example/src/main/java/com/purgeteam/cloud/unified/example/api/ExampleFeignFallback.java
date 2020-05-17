package com.purgeteam.cloud.unified.example.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试 FeignClient
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Slf4j
@Component
public class ExampleFeignFallback  implements ExampleFeignClient {

    @Override
    public String test() throws Exception {
        log.warn("ExampleFeignFallback 触发");
        return "ExampleFeignFallback";
    }

    @Override
    public Boolean testBoolean() throws Exception {
        return false;
    }

    @Override
    public Example example() throws Exception {
        log.warn("ExampleFeignFallback 触发");
        Example example = new Example();
        example.setName("ExampleFeignFallback");
        example.setAge("1");
        return example;
    }
}
