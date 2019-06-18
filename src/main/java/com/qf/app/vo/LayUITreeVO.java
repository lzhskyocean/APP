package com.qf.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 针对layui的树组件提供
 * @author 郑大仙丶
 * @date 2019-06-17 14:12
 */
@Data
@NoArgsConstructor
public class LayUITreeVO {

    private Long id;                    //3.

    private String title;               //三级分类

    private List<LayUITreeVO> children; //null

    public LayUITreeVO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
