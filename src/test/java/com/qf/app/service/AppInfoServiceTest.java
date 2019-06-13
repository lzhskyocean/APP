package com.qf.app.service;

import com.qf.app.AppApplicationTests;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.view.AppMaintain;
import com.qf.app.vo.AppDownloadsVO;
import com.qf.app.vo.LayUITableVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 11:43
 */
public class AppInfoServiceTest extends AppApplicationTests {

    @Autowired
    private AppInfoService appInfoService;

    @Test
    public void findDownloadsTopFive() {
//        List<AppDownloadsVO> voList = appInfoService.findDownloadsTopFive();
//        for (AppDownloadsVO appDownloadsVO : voList) {
//            System.err.println(appDownloadsVO);
//        }
    }

    @Test
    public void findByCondition(){
        AppInfoMaintainForm form = new AppInfoMaintainForm();
        form.setPage(1);
        form.setLimit(10);
        LayUITableVO<AppMaintain> vo = appInfoService.findByCondition(form);

        System.err.println(vo);
    }














}