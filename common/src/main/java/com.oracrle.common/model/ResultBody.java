package com.oracrle.common.model;


import com.oracrle.common.constants.ResultCodeEnum;

/**
 * @author: wjc
 * @createDate: 2020/9/8 14:27
 * @description:
 */
public class ResultBody {

    private String msg;

    private String code;

    private Object data;

    public static ResultBody ok(){
        return new ResultBody(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getValue(), null);
    }

    public static ResultBody ok(Object data){
        return new ResultBody(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getValue(), data);
    }

    public static ResultBody ok(String code, String msg){
        return new ResultBody(code, msg, null);
    }

    public static ResultBody faild(){
        return new ResultBody(ResultCodeEnum.FAILURE.getCode(), ResultCodeEnum.FAILURE.getValue(), null);
    }

    public static ResultBody faild(String msg){
        return new ResultBody(ResultCodeEnum.FAILURE.getCode(), msg, null);
    }

    public static ResultBody faild(String code, String msg){
        return new ResultBody(code, msg, null);
    }

    public static ResultBody exception(){
        return new ResultBody(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ResultCodeEnum.INTERNAL_SERVER_ERROR.getValue(), null);
    }
    
    public static ResultBody exception(String code, String msg){
        return new ResultBody(code, msg, null);
    }

    public ResultBody(String msg){
        this.msg = msg;
    }

    public ResultBody(String code, String msg){
        this.msg = msg;
        this.code = code;
    }

    public ResultBody(String code, String msg, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
