package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.AdminRole;
import com.wjc.gateway.vo.AdminRoleSaveVo;
import com.wjc.gateway.vo.AdminRoleUpdateVo;
import com.wjc.gateway.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IAdminRoleService extends IService<AdminRole> {

    AdminRole queryByRoleId(Long roleId);

    boolean saveAdminRole(AdminRole role);

    void save(AdminRoleSaveVo vo);

    void update(AdminRoleUpdateVo vo);

    void delete(Long roleId);

    List<Map<String, Object>> page(PageVo vo);
}
