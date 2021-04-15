package com.wjc.gateway.vo;

import lombok.Data;

/**
 * @author: wjc
 * @createDate: 2020/9/16 10:09
 * @description:
 */
@Data
public class AdminMenuSaveVo {

    private Long parentId;

    private Integer level;

    private String url;

    private String title;

    private String icon;

    private Integer sort;

    private Integer type;

}
