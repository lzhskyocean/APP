package com.qf.app.service;

import com.qf.app.bean.DevUser;

import javax.mail.MessagingException;
import java.util.List;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 14:42
 */
public interface DevUserService {

    //1. 注册账号.
    void register(DevUser devUser) throws MessagingException;

    //2. 激活账号.
    void active(String devUsername, String code);

    //3. 执行登录.
    DevUser login(String devUsername, String devPassword);

    //4. 异步校验用户名
    void devUserService(String devUsername);

    //5. 查询超过三天未激活账号的用户
    List<DevUser> findThreeDayNotActive();

    //6. 删除超过三天未激活账号的用户
    Integer deleteThreeDayNotActive(List<DevUser> devUserList);
}
