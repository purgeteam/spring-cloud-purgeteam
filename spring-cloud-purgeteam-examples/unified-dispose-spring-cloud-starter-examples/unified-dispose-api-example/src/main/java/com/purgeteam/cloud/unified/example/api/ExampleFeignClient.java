package com.purgeteam.cloud.unified.example.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试 FeignClient
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@FeignClient(value = "unified-dispose-b-example", url = "127.0.0.1:11001", fallback = ExampleFeignFallback.class)
public interface ExampleFeignClient {

    /**
     * 测试
     *
     * @return string
     */
    @GetMapping(value = "test")
    String test() throws Exception;

    /**
     * testBoolean
     * @return boolean
     */
    @GetMapping(value = "testBoolean")
    Boolean testBoolean() throws Exception;

    /**
     * 测试实体返回
     *
     * @return Example
     */
    @GetMapping(value = "example")
    Example example() throws Exception;
}
