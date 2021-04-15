package com.wjc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wjc.gateway.entity.AdminRole;
import com.wjc.gateway.entity.AdminRoleMenu;
import com.wjc.gateway.mapper.AdminRoleMapper;
import com.wjc.gateway.service.IAdminRoleMenuService;
import com.wjc.gateway.service.IAdminRoleService;
import com.wjc.gateway.service.IAdminUserService;
import com.wjc.gateway.vo.AdminRoleSaveVo;
import com.wjc.gateway.vo.AdminRoleUpdateVo;
import com.wjc.gateway.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Autowired
    private IAdminUserService userService;
    @Autowired
    private IAdminRoleMenuService adminRoleMenuService;

    @Override
    public AdminRole queryByRoleId(Long roleId) {
        return lambdaQuery().eq(AdminRole::getRoleId, roleId).one();
    }

    @Override
    public boolean saveAdminRole(AdminRole role) {
        return super.save(role);
    }

    @Override
    public void save(AdminRoleSaveVo vo) {
//        AdminUser user = userService.queryUserByToken();
//        AdminRole role = new AdminRole(LocalDateTime.now(), vo.getTitle(), user.getType(), user.getUserId());
        AdminRole role = new AdminRole(LocalDateTime.now(), vo.getTitle(), null, null);
        saveAdminRole(role);

        Long[] menuIds = vo.getMenuIds();
        List<AdminRoleMenu> list = new ArrayList<>(menuIds.length);
        for (Long menuId : menuIds) {
            AdminRoleMenu roleMenu = new AdminRoleMenu(role.getRoleId(), menuId);
            list.add(roleMenu);
        }

        adminRoleMenuService.saveBatch(list);
    }

    @Override
    public void update(AdminRoleUpdateVo vo) {
        Long roleId = vo.getRoleId();
        Long[] menuIds = vo.getMenuIds();

        AdminRole role = queryByRoleId(roleId);
        role.setTitle(vo.getTitle());

        adminRoleMenuService.removeByRoleId(roleId);

        if (null != menuIds){

            List<AdminRoleMenu> list = new ArrayList<>(menuIds.length);
            Stream.of(menuIds).forEach(menuId -> {
                AdminRoleMenu roleMenu = new AdminRoleMenu(role.getRoleId(), menuId);
                list.add(roleMenu);
            });

            adminRoleMenuService.saveBatch(list);
        }
    }

    @Override
    public void delete(Long roleId) {
        adminRoleMenuService.removeByRoleId(roleId);
        removeById(roleId);
    }

    @Override
    public List<Map<String, Object>> page(PageVo vo) {

        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        return baseMapper.page();
    }
}
