package com.wjc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.gateway.entity.AdminUserRole;
import com.wjc.gateway.mapper.AdminUserRoleMapper;
import com.wjc.gateway.service.IAdminUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole> implements IAdminUserRoleService {

    @Override
    public List<AdminUserRole> queryListByUserId(Long userId) {
        return lambdaQuery().eq(AdminUserRole::getUserId, userId).list();
    }

    @Override
    public void saveAdminUserRole(Long userId, Long roleId) {
        AdminUserRole adminUserRole = new AdminUserRole(userId, roleId);
        save(adminUserRole);
    }
}
