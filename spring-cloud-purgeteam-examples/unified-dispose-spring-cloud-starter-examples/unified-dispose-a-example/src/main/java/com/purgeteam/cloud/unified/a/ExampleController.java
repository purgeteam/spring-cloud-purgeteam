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

    @GetMapping("testBoolean")
    public Boolean testBoolean() throws Exception {
        // 调用服务端异常
        // 如果服务端不添加 @IgnoreResponseAdvice(errorDispose = false) 会出现下面异常
        // There was an unexpected error (type=Internal Server Error, status=500).
        // Error while extracting response for type [class java.lang.Boolean] and content type [application/json;charset=UTF-8]; nested exception is org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.lang.Boolean` out of START_OBJECT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.lang.Boolean` out of START_OBJECT token at [Source: (PushbackInputStream); line: 1, column: 1]
        return exampleFeignClient.testBoolean();
    }

    @GetMapping("example")
    public Example example() throws Exception {
        // 调用服务端异常 feign.FeignException: status 500 reading ExampleFeignClient#test()
        return exampleFeignClient.example();
    }
}
