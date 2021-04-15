package com.wjc.gateway.vo;

import lombok.Data;

/**
 * @author: wjc
 * @createDate: 2020/9/14 16:56
 * @description:
 */
@Data
public class ShopSaveAndManagerVo {

    private String title;

    private String address;

    private String lat;

    private String lon;

    private String logo;

    private String intro;

    private String note;

    private Integer status;

    private ShopSaveManagerVo manager;
}
