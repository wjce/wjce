package com.wjc.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.server.entity.ProductSpec;
import com.wjc.server.mapper.ProductSpecMapper;
import com.wjc.server.service.ProductSpecService;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpec> implements ProductSpecService {
}
