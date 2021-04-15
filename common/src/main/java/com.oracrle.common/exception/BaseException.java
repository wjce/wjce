package com.oracrle.common.exception;


import com.oracrle.common.constants.ResultCodeEnum;

/**
 * @author: wjc
 * @createDate: 2020/9/15 16:22
 * @description:
 */
public class BaseException extends RuntimeException{

    private String code;

    private String msg;

    public BaseException() {
        this.code = ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode();
        this.msg = ResultCodeEnum.INTERNAL_SERVER_ERROR.getValue();
    }
    public BaseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
