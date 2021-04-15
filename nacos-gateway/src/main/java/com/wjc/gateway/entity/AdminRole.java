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
 * 角色表
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kcallme_admin_role")
public class AdminRole extends Model<AdminRole> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long roleId;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 角色名称
     */
    private String title;
    /**
     * 创建人类型 1商户 2门店
     */
    private Integer createType;
    /**
     * 创建人id
     */
    private Long createUserId;

    public AdminRole() {}

    public AdminRole(LocalDateTime createTime, String title, Integer createType, Long createUserId) {
        this.createTime = createTime;
        this.title = title;
        this.createType = createType;
        this.createUserId = createUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
