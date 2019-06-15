package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.AppVersion;
import com.qf.app.enums.PublishStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-14 17:39
 */
public class AppVersionMapperTest extends AppApplicationTests {

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Test
    public void findByAppIdOrderByUpdatedDescLimit() {
        List<AppVersion> list =
                appVersionMapper.findByAppIdOrderByUpdatedDescLimit(1L);

        for (AppVersion appVersion : list) {
            System.err.println(appVersion);
        }
    }


    @Test
    @Transactional
    public void save(){
        AppVersion appVersion = new AppVersion();
        appVersion.setAppId(1L);
        appVersion.setVersionNo("S12");
        appVersion.setVersionSize("1234");
        appVersion.setApkFileName("xxx.apk");
        appVersion.setDownloadLink("http://xxx.apk");
        appVersion.setVersionInfo("xxx特别好!");

        // service
        appVersion.setPublishStatus(PublishStatusEnum.READY_PUBLISH.getStatus());

        // 测试
        int count = appVersionMapper.insertSelective(appVersion);

        assertEquals(count,1);

        System.err.println(appVersion);

    }












}