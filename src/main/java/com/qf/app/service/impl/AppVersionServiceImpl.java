package com.qf.app.service.impl;

import com.qf.app.bean.AppInfo;
import com.qf.app.bean.AppVersion;
import com.qf.app.enums.PublishStatusEnum;
import com.qf.app.exception.AppException;
import com.qf.app.mapper.AppVersionMapper;
import com.qf.app.service.AppInfoService;
import com.qf.app.service.AppVersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * App版本的service实现类
 * @author 郑大仙丶
 * @date 2019-06-14 17:44
 */
@Service
@Slf4j
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Autowired
    @Lazy
    private AppInfoService appInfoService;


    @Override
    public List<AppVersion> findNewVersionThree(Long appId) {
        return appVersionMapper.findByAppIdOrderByUpdatedDescLimit(appId);
    }

    @Override
    @Transactional
    public void save(AppVersion appVersion) {
        //1. 封装数据.
        appVersion.setPublishStatus(PublishStatusEnum.READY_PUBLISH.getStatus());
        //2. 添加appVersion (判断是否成功)
        int count = appVersionMapper.insertSelective(appVersion);
        if(count != 1){
            log.error("[添加APP版本] 添加App版本失败 appVersion = {}",appVersion);
            throw new AppException("添加App版本失败,请联系管理员");
        }
        //3. 封装AppInfo
        AppInfo appInfo = new AppInfo();
        appInfo.setId(appVersion.getAppId());
        appInfo.setVersionId(appVersion.getId());
        //4. 调用appInfoService修改数据.
        appInfoService.updateVersionId(appInfo);
    }
}
