package com.qf.app.exception;

import lombok.Getter;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 14:54
 */
@Getter
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }
}
