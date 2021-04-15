package com.oracrle.common.constants;

/**
 * @author: wjc
 * @createDate: 2020/9/8 14:29
 * @description:
 */
public enum ResultCodeEnum {
    SUCCESS( "200", "SUCCESS" ),
    FAILURE( "400", "FAILURE" ),
    UN_LOGIN( "401", "未登录" ),
    UNAUTHORIZED( "403", "未认证或Token失效" ),
    USER_UNAUTHORIZED( "402", "用户名或密码不正确" ),
    NOT_FOUND( "404", "接口不存在" ),
    INTERNAL_SERVER_ERROR( "500", "服务器内部错误" ),
    /** 用户 */
    MSD_CODE_ERROR("100001", "验证码错误"),
    ADMIN_USER_NOT_EXISTS("100002", "用户不存在"),
    ADMIN_USER_EXISTS("100003", "用户已存在"),
    ;

    private String code;

    private String value;

    ResultCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
