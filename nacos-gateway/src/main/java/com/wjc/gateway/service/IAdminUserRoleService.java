package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.AdminUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IAdminUserRoleService extends IService<AdminUserRole> {

    List<AdminUserRole> queryListByUserId(Long userId);

    void saveAdminUserRole(Long userId, Long roleId);
}
