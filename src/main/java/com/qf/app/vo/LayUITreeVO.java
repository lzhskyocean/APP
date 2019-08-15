package com.qf.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 针对layui的树组件提供
 * @author 郑大仙丶
 * @date 2019-06-17 14:12
 */
@Data  // 进行get/set/toString等操作
@NoArgsConstructor  // 无参构造
public class LayUITreeVO {

    private Long id;

    private String title;

    private List<LayUITreeVO> children;

    public LayUITreeVO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
