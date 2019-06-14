package com.qf.app.service;

import com.qf.app.bean.AppInfo;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.view.AppMaintain;
import com.qf.app.vo.AppDownloadsVO;
import com.qf.app.vo.LayUITableVO;

import javax.validation.Valid;
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

    /**
     * app维护页面需要的表格数据
     * @param appInfoMaintainForm
     * @return
     */
    LayUITableVO<AppMaintain> findByCondition(AppInfoMaintainForm appInfoMaintainForm);

    /**
     * 添加APP基础信息
     * @param appInfo
     */
    void appInfoAdd(AppInfo appInfo);
}
