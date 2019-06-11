package com.qf.app.service.impl;

import com.qf.app.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的.
 * @author 郑大仙丶
 * @date 2019-06-11 15:03
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    @Async
    public void sendHTMLMail(String to, String subject, String content) throws MessagingException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1. 创建一份邮件.
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        // 设置发件人
        messageHelper.setFrom(from);
        // 设置收件人
        messageHelper.addTo(to);
        // 设置主题.
        messageHelper.setSubject(subject);
        // 设置内容
        messageHelper.setText(content,true);
        //2. 发送邮件.
        javaMailSender.send(message);
    }













}
