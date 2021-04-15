package com.wjc.gateway.vo;

import lombok.Data;

/**
 * @author: wjc
 * @createDate: 2020/9/14 16:59
 * @description:
 */
@Data
public class ShopSaveManagerVo {

    private String name;

    private String mobile;

    private Long roleId;

    private Long userPositionId;

    private String username;

    private String password;
}
