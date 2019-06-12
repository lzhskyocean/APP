package com.qf.app.service;

import com.qf.app.vo.AppDownloadsVO;

import java.util.List;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 11:38
 */
public interface AppInfoService {
    /**
     * 查询下载量TOP FIVE
     * @return
     */
    String findDownloadsTopFive();
}
