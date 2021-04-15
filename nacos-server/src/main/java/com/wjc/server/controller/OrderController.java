package com.wjc.server.controller;

import com.wjc.server.entity.Order;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wjc.server.service.OrderService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjc
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public String save(){
        Order order = new Order();
        order.setUserId(RandomUtils.nextLong(100000000000000000l,999999999999999999l));
        return "success";
    }


}
