package com.purgeteam.cloud.unified.a;

import com.purgeteam.cloud.dispose.starter.annotation.IgnoreResponseAdvice;
import com.purgeteam.cloud.dispose.starter.exception.category.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类忽略拦截测试 {@link IgnoreResponseAdvice#errorDispose()} 异常包装处理
 * errorDispose = false 不进行处理异常返回包装，抛出原有异常。
 * errorDispose = true 进行处理异常返回包装，返回包装内容。
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@RestController
@IgnoreResponseAdvice(errorDispose = false)
@RequestMapping("ignor")
public class IgnoreResponseAdviceController {

    /**
     * 全局异常处理
     */
    @GetMapping("error")
    public String error() {
        throw new BusinessException("0", "异常演示");
    }

    /**
     * 全局异常处理 errorDispose 为 true 处理返回包装
     */
    @GetMapping("error1")
    @IgnoreResponseAdvice(errorDispose = true)
    public String error1() {
        throw new BusinessException("0", "异常演示");
    }

}
