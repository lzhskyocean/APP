package com.qf.app.enums;

import lombok.Getter;

/**
 * App状态枚举
 * @author 郑大仙丶
 * @date 2019-06-14 16:24
 */
@Getter
public enum AppStatusEnum {

    WAIT_EXAM(1L,"待审核"),
    EXAM_PASS(2L,"审核通过"),
    EXAM_NOT(3L,"审核未通过"),
    ON_SALE(4L,"上架"),
    OFF_SALE(5L,"下架");

    private Long status;

    private String msg;

    AppStatusEnum(Long status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
