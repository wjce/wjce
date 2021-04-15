package com.wjc.server.service.impl;

import com.wjc.server.entity.Order;
import com.wjc.server.mapper.OrderMapper;
import com.wjc.server.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-11-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
