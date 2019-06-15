package com.qf.app.service;

import com.qf.app.bean.AppVersion;

import javax.validation.Valid;
import java.util.List;

/**
 * App的版本service
 * @author 郑大仙丶
 * @date 2019-06-14 17:44
 */
public interface AppVersionService {

    // 查询最新的三个历史版本
    List<AppVersion> findNewVersionThree(Long appId);

    // 添加APP版本
    void save(AppVersion appVersion);
}
