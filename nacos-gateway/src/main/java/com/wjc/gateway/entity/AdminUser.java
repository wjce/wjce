package com.wjc.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kcallme_admin_user")
public class AdminUser extends Model<AdminUser> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
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

    private String salt;
    /**
     * 用户类型 0总部 1店长 2店员
     */
    private Integer type;
    /**
     * 是否试用 0否 1是
     */
    private Integer isTrial;
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

    public AdminUser() {}

    public AdminUser(LocalDateTime createTime, String username, String password, String salt, Integer type, Integer isTrial, Integer status) {
        this.createTime = createTime;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.type = type;
        this.isTrial = isTrial;
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
