package com.wjc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.gateway.entity.UserPosition;
import com.wjc.gateway.mapper.UserPositionMapper;
import com.wjc.gateway.service.IUserPositionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户职位表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class UserPositionServiceImpl extends ServiceImpl<UserPositionMapper, UserPosition> implements IUserPositionService {

    @Override
    public void saveUserPosition(Long userId, Long shopId, Long... positionIds) {
        LocalDateTime dateTime = LocalDateTime.now();
        List<UserPosition> list = new ArrayList<>(positionIds.length);
        for (Long positionId : positionIds) {
            UserPosition userPosition = new UserPosition(dateTime, userId, positionId, shopId);
            list.add(userPosition);
        }

        saveBatch(list);
    }
}
