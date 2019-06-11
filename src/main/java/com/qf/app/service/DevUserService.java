package com.qf.app.service;

import com.qf.app.bean.DevUser;

import javax.mail.MessagingException;

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
}
