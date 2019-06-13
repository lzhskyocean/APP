package com.qf.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * layUI中的table组件需要的返回结果VO.
 * @author 郑大仙丶
 * @date 2019-06-13 12:01
 */
@Data
@NoArgsConstructor
public class LayUITableVO<T> implements Serializable {

    // 成功返回0
    private Integer code;

    // ""
    private String msg;

    // 数据总条数
    private Long count;

    // 具体数据
    private List<T> data;

    public LayUITableVO(Long count, List<T> data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }
}
