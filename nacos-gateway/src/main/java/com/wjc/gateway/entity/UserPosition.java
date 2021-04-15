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
 * 用户职位表
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kcallme_user_position")
public class UserPosition extends Model<UserPosition> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long userPositionId;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 职位id
     */
    private Long positionId;
    /**
     * 店铺id
     */
    private Long shopId;

    @Override
    protected Serializable pkVal() {
        return this.userPositionId;
    }

    public UserPosition() {}

    public UserPosition(LocalDateTime createTime, Long userId, Long positionId, Long shopId) {
        this.createTime = createTime;
        this.userId = userId;
        this.positionId = positionId;
        this.shopId = shopId;
    }
}
