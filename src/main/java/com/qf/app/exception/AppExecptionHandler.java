package com.qf.app.exception;

import com.qf.app.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * APP项目的异常处理器
 * create by 郑大仙丶
 * 2019/6/18 23:18
 */
@ControllerAdvice
public class AppExecptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResultVO appException(AppException ex){
        return new ResultVO(1,ex.getMessage());
    }
}
