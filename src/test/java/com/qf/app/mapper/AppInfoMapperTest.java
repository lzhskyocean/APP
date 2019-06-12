package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.AppInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 11:35
 */
public class AppInfoMapperTest extends AppApplicationTests {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Test
    public void findOrderByDownloadsDescLimit() {
        List<AppInfo> list = appInfoMapper.findOrderByDownloadsDescLimit();

        for (AppInfo appInfo : list) {
            System.err.println(appInfo);
        }
    }
}