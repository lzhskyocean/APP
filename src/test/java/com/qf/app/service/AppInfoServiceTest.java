package com.qf.app.service;

import com.qf.app.AppApplicationTests;
import com.qf.app.vo.AppDownloadsVO;
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
}