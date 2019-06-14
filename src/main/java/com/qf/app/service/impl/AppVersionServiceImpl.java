package com.qf.app.service.impl;

import com.qf.app.bean.AppVersion;
import com.qf.app.mapper.AppVersionMapper;
import com.qf.app.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * App版本的service实现类
 * @author 郑大仙丶
 * @date 2019-06-14 17:44
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;


    @Override
    public List<AppVersion> findNewVersionThree(Long appId) {
        return appVersionMapper.findByAppIdOrderByUpdatedDescLimit(appId);
    }
}
