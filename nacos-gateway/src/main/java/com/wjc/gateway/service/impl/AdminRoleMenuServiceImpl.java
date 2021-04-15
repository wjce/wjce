package com.wjc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.gateway.entity.AdminRoleMenu;
import com.wjc.gateway.mapper.AdminRoleMenuMapper;
import com.wjc.gateway.service.IAdminRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class AdminRoleMenuServiceImpl extends ServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements IAdminRoleMenuService {

    @Override
    public List<AdminRoleMenu> queryListByRoleId(Long roleId) {
        return lambdaQuery().eq(AdminRoleMenu::getRoleId, roleId).list();
    }

    @Override
    public void saveBatch(List<AdminRoleMenu> list) {
        super.saveBatch(list);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        List<AdminRoleMenu> old = queryListByRoleId(roleId);
        if(!old.isEmpty()){
            removeByIds(old.stream().map(AdminRoleMenu::getRoleMenuId).collect(Collectors.toList()));
        }
    }

    @Override
    public List<AdminRoleMenu> queryListByMenuId(Long menuId) {
        return lambdaQuery().eq(AdminRoleMenu::getMenuId, menuId).list();
    }
}
