package com.qf.app.enums;

import lombok.Getter;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 14:50
 */
@Getter
public enum DevUserStateEnum {

    NOT_ACTIVE(0,"未激活"),
    ACTIVE(1,"已激活");

    private Integer state;

    private String msg;

    DevUserStateEnum(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }
}
