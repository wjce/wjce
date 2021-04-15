package com.wjc.gateway.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: wjc
 * @createDate: 2020/9/10 14:20
 * @description: 修改爵色
 */
@Data
public class AdminRoleUpdateVo {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @NotBlank(message = "角色名称不能为空")
    private String title;

    private Long[] menuIds;

}
