package com.qf.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * APP下载量TOP FIVE对应的VO.
 * @author 郑大仙丶
 * @date 2019-06-12 11:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDownloadsVO implements Serializable {

    /**
     * 软件名称
     */
    private String softwareName;

    /**
     * 下载次数
     */
    private Long downloads;

}
