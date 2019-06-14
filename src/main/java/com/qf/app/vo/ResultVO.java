package com.qf.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共的返回数据格式
 * @author 郑大仙丶
 * @date 2019-06-14 16:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {

    private Integer code = 0;

    private String msg = "";

    private Object data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
