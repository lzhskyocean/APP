package com.qf.app.service.impl;

import com.qf.app.bean.DevUser;
import com.qf.app.enums.DevUserStateEnum;
import com.qf.app.exception.AppException;
import com.qf.app.mapper.DevUserMapper;
import com.qf.app.service.DevUserService;
import com.qf.app.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import static java.util.UUID.randomUUID;

/**
 * 开发者用户模块的service.
 * @author 郑大仙丶
 * @date 2019-06-11 14:42
 */
@Service
@Slf4j
public class DevUserServiceImpl implements DevUserService {

    @Autowired
    private DevUserMapper devUserMapper;

    @Autowired
    private MailService mailService;


    @Value("${app.activeUrl}")
    private String activeUrl;



    // 加密次数
    private final int HASH_ITERATIONS = 1024;


    /**
     * 注册账号.
     * @param devUser
     */
    @Override
    public void register(DevUser devUser) throws MessagingException {
        //1. 生成盐.
        String salt = randomUUID().toString();
        devUser.setDevSalt(salt);
        //2. 对密码进行加密.
        String password = new Md5Hash(devUser.getDevPassword(),devUser.getDevSalt(),HASH_ITERATIONS).toString();
        devUser.setDevPassword(password);
        //3. 设置state为0.未激活状态.
        devUser.setDevState(DevUserStateEnum.NOT_ACTIVE.getState());
        //4. 生成code码.
        String code = randomUUID().toString() + randomUUID().toString();
        devUser.setDevCode(code);
        //5. 调用mapper.保存数据.
        int count = devUserMapper.insertSelective(devUser);
        if(count != 1){
            // 添加失败.
            log.error("[注册账号]  注册账号失败 devUser = {}",devUser);
            throw new AppException("注册账号失败,请联系管理员,电话:110!");
        }
        //6. 发送激活路径到邮箱.
        String to = devUser.getDevEmail();
        String subject = "APP管理系统-激活开发者平台账号";
        String content = String.format("<h1><a href='%s?devUsername=%s&code=%s'>激活开发者账号</a></h1>", activeUrl, devUser.getDevUsername(), devUser.getDevCode()).toString();
        System.out.println(content);
        mailService.sendHTMLMail(to,subject,content);
    }

    /**
     * 激活账号
     * @param devUsername
     * @param code
     */
    @Override
    public void active(String devUsername, String code) {
        //1. 封装查询条件.
        DevUser param = new DevUser();
        param.setDevUsername(devUsername);
        //2. 查询用户信息.
        DevUser devUser = devUserMapper.selectOne(param);
        //3. 如果为null.
        if(devUser == null) {
            log.info("[激活账号] 未查询到用户信息 param = {}" ,param);
            throw new AppException("未找到指定的用户!");
        }
        //4. 判断用户的code码是否一致.
        if(!code.equals(devUser.getDevCode()) || devUser.getDevCode() == null){
            log.info("[激活账号] 用户的code码不合法. code = {} , devCode = {}" ,code,devUser.getDevCode());
            throw new AppException("用户已经激活,或code码已经过期!!");
        }
        //5. 修改用户信息 state -> 1   code -> null
        devUser.setDevState(DevUserStateEnum.ACTIVE.getState());
        devUser.setDevCode(null);
        //6. 执行修改
        int count = devUserMapper.updateByPrimaryKey(devUser);
        if(count != 1){
            log.error("[激活账号] 激活账号失败 devUser = {}",devUser);
            throw new AppException("激活账号失败!");
        }
    }


    /**
     * 执行登录.
     * @param devUsername
     * @param devPassword
     * @return
     */
    @Override
    public DevUser login(String devUsername, String devPassword) {
        //1. 封装查询条件.
        DevUser param = new DevUser();
        param.setDevUsername(devUsername);
        //2. 查询用户信息.
        DevUser devUser = devUserMapper.selectOne(param);
        if(devUser == null){
            // 用户名错误
            log.info("[登录] 当前用户不存在  param = {}" , param);
            throw new AppException("当前用户不存在!");
        }
        //3. 判断用户状态. -> 如果为0 ->
        if(devUser.getDevState() != DevUserStateEnum.ACTIVE.getState()){
            //用户未激活.
            log.info("[登录] ,用户未激活! devUser = {}",devUser);
            throw new AppException("用户未激活!");
        }
        //4. 对比密码是否一致.
        String password = new Md5Hash(devPassword, devUser.getDevSalt(), HASH_ITERATIONS).toString();
        if(!devUser.getDevPassword().equals(password)){
            // 密码错误
            log.info("[登录] ,用户密码错误! devUser = {} , password = {}",devUser,password);
            throw new AppException("用户密码错误!");
        }
        //5. 如果密码一致,登录成功,返回devUser
        return devUser;
    }
}
