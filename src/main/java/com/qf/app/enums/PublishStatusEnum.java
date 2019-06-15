package com.qf.app.enums;

import lombok.Getter;

/**
 * App版本发布状态枚举
 * @author 郑大仙丶
 * @date 2019-06-14 16:24
 */
@Getter
public enum PublishStatusEnum {

    NO_PUBLISH(1L,"不发布"),
    ALREADY_PUBLISH(2L,"已发布"),
    READY_PUBLISH(3L,"预发布"),
   ;

    private Long status;

    private String msg;

    PublishStatusEnum(Long status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
