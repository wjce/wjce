package com.wjc.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("t_product_spec")
@Data
public class ProductSpec extends Model<ProductSpec> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long productId;

    private String title;

    private BigDecimal price;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
