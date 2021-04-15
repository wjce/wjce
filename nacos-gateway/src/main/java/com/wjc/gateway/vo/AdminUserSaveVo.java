package com.wjc.gateway.vo;

import lombok.Data;

/**
 * @author: wjc
 * @createDate: 2020/9/15 15:02
 * @description:
 */
@Data
public class AdminUserSaveVo {

    /**
     * 姓名
     */
    private String name;
    /**
     * 性别 1男 2女
     */
    private Integer sex;
    /**
     * 头像
     */
    private Integer headImg;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态 0正常 1冻结
     */
    private Integer status;
    /**
     * 备注
     */
    private String note;
    /**
     * 员工工号
     */
    private String code;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 门店id
     */
    private Long shopId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 职位ids
     */
    private Long[] positionIds;

}
