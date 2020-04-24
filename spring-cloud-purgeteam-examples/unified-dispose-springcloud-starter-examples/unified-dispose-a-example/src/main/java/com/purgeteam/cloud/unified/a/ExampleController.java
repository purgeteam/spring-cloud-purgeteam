package com.purgeteam.cloud.unified.a;

import com.purgeteam.cloud.unified.example.api.Example;
import com.purgeteam.cloud.unified.example.api.ExampleFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * test
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@RestController
public class ExampleController {

    @Resource
    private ExampleFeignClient exampleFeignClient;

    @GetMapping("test")
    public String test() throws Exception {
        // 调用服务端异常 feign.FeignException: status 500 reading ExampleFeignClient#test()
        return exampleFeignClient.test();
    }

    @GetMapping("example")
    public Example example() throws Exception {
        // 调用服务端异常 feign.FeignException: status 500 reading ExampleFeignClient#test()
        return exampleFeignClient.example();
    }
}
