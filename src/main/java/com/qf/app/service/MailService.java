package com.qf.app.service;

import javax.mail.MessagingException;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 15:02
 */
public interface MailService {


    //  发送一封邮件
    void sendHTMLMail(String to,String subject,String content) throws MessagingException;

}
