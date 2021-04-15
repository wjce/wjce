package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.UserPosition;

/**
 * <p>
 * 用户职位表 服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IUserPositionService extends IService<UserPosition> {

    void saveUserPosition(Long userId, Long shopId, Long...  positionIds);
}
