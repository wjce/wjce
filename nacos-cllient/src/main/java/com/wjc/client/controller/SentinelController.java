package com.wjc.client.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc
 * @description
 * @date 2020/10/10
 */
@RequestMapping("/sentinel")
@RestController
public class SentinelController {

    @SentinelResource(value = "sentinel_test")
    @GetMapping("/test")
    public String sentinelTest(){
        return "测试限流，请求通过";
    }
}
