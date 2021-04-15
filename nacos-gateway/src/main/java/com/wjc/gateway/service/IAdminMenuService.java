package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.AdminMenu;
import com.wjc.gateway.vo.AdminMenuSaveVo;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IAdminMenuService extends IService<AdminMenu> {

    AdminMenu queryByUrl(String url);

    void save(AdminMenuSaveVo vo);
}
