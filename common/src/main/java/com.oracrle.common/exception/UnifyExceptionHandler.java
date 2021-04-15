package com.oracrle.common.exception;

import com.oracrle.common.model.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wjc
 * @createDate: 2020/9/15 16:20
 * @description:
 */
@ControllerAdvice
@Slf4j
public class UnifyExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResultBody error(BaseException e){
        log.error(e.getMessage());
        return ResultBody.exception(e.getCode(), e.getMsg());
    }

}
