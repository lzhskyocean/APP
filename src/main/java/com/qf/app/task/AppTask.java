package com.qf.app.task;

import com.qf.app.bean.DevUser;
import com.qf.app.service.DevUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * APP项目的定时任务.
 * create by 郑大仙丶
 * 2019/6/18 23:38
 */
@Component
@Slf4j
public class AppTask {

    @Autowired
    private DevUserService devUserService;

    @Scheduled(cron = "0 * * * * ?")
    public void deleteNotActiveCode(){
        //1. 查询三天以上未激活的账号.
        List<DevUser> devUserList = devUserService.findThreeDayNotActive();
        //2. 如果有未激活的账号,删除.
        if(devUserList != null && devUserList.size() > 0){
            // 有未激活的账号
            log.info("[超过三天未激活账号] 账号信息 = {}",devUserList);
            Integer count = devUserService.deleteThreeDayNotActive(devUserList);
            // 判断删除是否成功
            if(devUserList.size() != count){
                log.error("[删除未激活账号错误] devUserList = {}",devUserList);
            }
        }
    }



}
