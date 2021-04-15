package com.wjc.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("t_product")
@Data
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String title;

    private BigDecimal price;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
