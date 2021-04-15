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
 * 菜单表
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kcallme_admin_menu")
public class AdminMenu extends Model<AdminMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long menuId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 路径
     */
    private String url;
    /**
     * 名称
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 类型 1菜单 2按钮
     */
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
