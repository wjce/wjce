package com.wjc.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjc.gateway.entity.AdminRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    List<Map<String, Object>> page();

}
