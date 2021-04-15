package com.wjc.gateway.vo;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author: wjc
 * @createDate: 2020/9/10 15:00
 * @description:
 */
@Data
public class PageVo {
    @Min(0)
    private int pageNum = 1;

    @Min(0)
    private int pageSize = 10;


}
