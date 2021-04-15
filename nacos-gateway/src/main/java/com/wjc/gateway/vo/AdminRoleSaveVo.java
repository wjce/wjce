package com.wjc.gateway.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: wjc
 * @createDate: 2020/9/10 11:48
 * @description: 保存角色
 */
@Data
public class AdminRoleSaveVo {

    @NotBlank(message = "角色名称不能为空")
    private String title;

    private Long[] menuIds;
}
