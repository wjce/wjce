package com.wjc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.gateway.entity.Position;
import com.wjc.gateway.mapper.PositionMapper;
import com.wjc.gateway.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
