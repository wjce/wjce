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
 * 职位表
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kcallme_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long positionId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 是否删除 0否 1是
     */
    private Integer isDel = 0;
    /**
     * 职位名称
     */
    private String title;
    /**
     * 创建人类型 1商家 2门店
     */
    private Integer createUserType;
    /**
     * 创建人id
     */
    private Long createUserId;


    @Override
    protected Serializable pkVal() {
        return this.positionId;
    }

}
