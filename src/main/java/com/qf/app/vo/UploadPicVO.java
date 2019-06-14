package com.qf.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传图片响应的数据格式
 * @author 郑大仙丶
 * @date 2019-06-14 11:40
 */
@Data
@NoArgsConstructor
public class UploadPicVO {

    private Integer code = 0;

    private String msg = "";

    private Object picPath;

    public UploadPicVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
