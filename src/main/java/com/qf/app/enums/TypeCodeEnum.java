package com.qf.app.enums;

import lombok.Getter;

/**
 * 对应字典表中的TypeCode
 * @author 郑大仙丶
 * @date 2019-06-12 16:31
 */
@Getter
public enum TypeCodeEnum {

    USER_TYPE("USER_TYPE","用户类型"),
    APP_STATUS("APP_STATUS","APP状态"),
    APP_FLATFORM("APP_FLATFORM","所属平台"),
    PUBLISH_STATUS("PUBLISH_STATUS","发布状态"),
    ;

    private String typeCode;

    private String typeName;

    TypeCodeEnum(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }
}
