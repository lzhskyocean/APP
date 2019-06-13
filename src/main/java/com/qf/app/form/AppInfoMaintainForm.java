package com.qf.app.form;

import lombok.Data;

/**
 * 接收查询app信息的全部条件
 * @author 郑大仙丶
 * @date 2019-06-13 11:59
 */
@Data
public class AppInfoMaintainForm {

    // 当前页
    private Integer page;
    // 每页显示条数
    private Integer limit;
    // 软件名称
    private String softwareName;
    // 所属平台id
    private Integer flatformId;
    // app状态id
    private Integer appStatusId;
    // 一级分类id
    private Long categoryLevelOne;
    // 二级分类id
    private Long categoryLevelTwo;
    // 三级分类id
    private Long categoryLevelThree;
}
