package com.qf.app.service;

import com.qf.app.AppApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 15:12
 */
public class MailServiceTest extends AppApplicationTests {

    @Autowired
    private MailService mailService;


    @Test
    public void sendHTMLMail() throws MessagingException {
        String to = "402424668@qq.com";
        String subject = "测试springboot发送邮件";
        String content = "<h1>测试springboot发送邮件</h1>";
        mailService.sendHTMLMail(to,subject,content);
    }













}