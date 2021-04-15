package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.AdminRoleMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IAdminRoleMenuService extends IService<AdminRoleMenu> {

    List<AdminRoleMenu> queryListByRoleId(Long roleId);

    void saveBatch(List<AdminRoleMenu> list);

    void removeByRoleId(Long roleId);

    List<AdminRoleMenu> queryListByMenuId(Long menuId);
}
