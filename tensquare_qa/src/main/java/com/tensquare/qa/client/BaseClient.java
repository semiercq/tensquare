package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author semiercq
 * @date 2020/11/22
 **/
@FeignClient(value = "tensquare-base")
public interface BaseClient {
    /**
     * \@GetMapping注解用于对被调用的微服务进行地址映射
     * \@PathVariable注解一定要指定参数名称，否则出错
     *
     * @param id id
     * @return Result
     */
    @GetMapping("/label/{id}")
    Result findById(@PathVariable("id") String id);

}
