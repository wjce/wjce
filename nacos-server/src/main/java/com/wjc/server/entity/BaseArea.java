package com.wjc.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjc
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_area")
public class BaseArea extends Model<BaseArea> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String pid;
    private String shortname;
    private String name;
    private String mergerName;
    private String level;
    private String pinyin;
    private String code;
    private String zipCode;
    private String lng;
    private String lat;
    private String vueCode;
    private String disCode;
    private String latitude;
    private String longitude;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
