package com.purgeteam.cloud.unified.dispose.examples.b;

import com.purgeteam.cloud.dispose.starter.annotation.IgnoreResponseAdvice;
import com.purgeteam.cloud.unified.example.api.Example;
import com.purgeteam.cloud.unified.example.api.ExampleFeignClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@RestController
public class ExampleController implements ExampleFeignClient {

    @Override
    public String test() throws Exception {
        throw new Exception("模拟异常");
    }

    /**
     * 添加 IgnoreResponseAdvice#errorDispose 设置为异常不需要处理包装
     */
    @Override
    @IgnoreResponseAdvice(errorDispose = false)
    public Boolean testBoolean() throws Exception {
        throw new Exception("模拟异常");
    }

    @Override
    public Example example() throws Exception {
        throw new Exception("模拟异常");
    }
}
