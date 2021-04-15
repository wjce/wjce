package com.wjc.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc
 * @description
 * @date 2020/10/10
 */
@RequestMapping("/gateway")
@RestController
public class GatewaySentinelController {

    @GetMapping("/sentinel/yml/test")
    public String sentinelTest(){
        return "测试网关全局限流配置，请求通过";
    }

    @GetMapping("/sentinel/config/test")
    public String sentinelConfigTest(){
        return "测试网关全局限流config，请求通过";
    }
}
