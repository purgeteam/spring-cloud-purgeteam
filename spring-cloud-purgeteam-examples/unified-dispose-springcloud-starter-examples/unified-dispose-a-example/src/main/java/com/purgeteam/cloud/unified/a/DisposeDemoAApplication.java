package com.purgeteam.cloud.unified.a;

import com.purgeteam.cloud.unified.example.api.ExampleFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 异常拦截测试 a
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@EnableFeignClients(basePackageClasses = {ExampleFeignClient.class})
@SpringBootApplication(scanBasePackages = {"com.purgeteam.cloud.unified.a", "com.purgeteam.cloud.unified.example.api"})
public class DisposeDemoAApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisposeDemoAApplication.class, args);
    }
}
