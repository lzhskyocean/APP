package com.qf.app.service;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.DevUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 15:39
 */
public class DevUserServiceTest extends AppApplicationTests {

    @Autowired
    private DevUserService devUserService;

    @Test
    public void register() throws MessagingException {
        DevUser devUser = new DevUser();
        devUser.setDevUsername("123123");
        devUser.setDevPassword("123123");
        devUser.setDevEmail("402424668@qq.com");
        devUser.setDevBirthday(new Date());

        devUserService.register(devUser);
    }


    @Test
    public void active(){
        String username = "123123";
        String code = "70c8ff9e-0a5a-4a1f-a043-7f4ea328d2be88aea33b-d327-4ecd-8f47-d4de36ec9119";

        devUserService.active(username,code);
    }


    @Test
    public void login(){
        String username = "zhangsan";
        String password = "123123";

        devUserService.login(username,password);
    }
































}