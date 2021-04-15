package com.wjc.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.gateway.entity.AdminMenu;
import com.wjc.gateway.mapper.AdminMenuMapper;
import com.wjc.gateway.service.IAdminMenuService;
import com.wjc.gateway.vo.AdminMenuSaveVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu> implements IAdminMenuService {

    @Override
    public AdminMenu queryByUrl(String url) {
        return lambdaQuery().eq(AdminMenu::getUrl, url).one();
    }

    @Override
    public void save(AdminMenuSaveVo vo) {
        AdminMenu adminMenu = JSON.parseObject(JSON.toJSONString(vo), AdminMenu.class);
        adminMenu.setCreateTime(LocalDateTime.now());
        save(adminMenu);
    }
}
