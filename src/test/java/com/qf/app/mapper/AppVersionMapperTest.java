package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.AppVersion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-14 17:39
 */
public class AppVersionMapperTest extends AppApplicationTests {

    @Autowired
    private AppVersionMapper mapper;

    @Test
    public void findByAppIdOrderByUpdatedDescLimit() {
        List<AppVersion> list =
                mapper.findByAppIdOrderByUpdatedDescLimit(1L);

        for (AppVersion appVersion : list) {
            System.err.println(appVersion);
        }

    }











}