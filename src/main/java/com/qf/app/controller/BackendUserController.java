package com.qf.app.controller;

import static com.qf.app.constant.AppConstant.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 后台管理用户模块.
 * @author 郑大仙丶
 * @date 2019-06-17 09:34
 */
@Controller
@RequestMapping("/backend/user")
public class BackendUserController {

    /**
     * 跳转到登录页面.
     * @return
     */
    @GetMapping("/login")
    public String toLoginPage(){
        return BACKEND_USER_LOGIN_PAGE;
    }


    /**
     * 执行登录
     * @param backendCode
     * @param backendPassword
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String login(String backendCode, String backendPassword,
                        RedirectAttributes redirectAttributes) {
        //1. 校验参数.
        if(StringUtils.isEmpty(backendCode) || StringUtils.isEmpty(backendPassword)){
            // 用户名和密码是必填项
            redirectAttributes.addAttribute("loginInfo","用户名和密码为必填项..");
            return REDIRECT + BACKEND_USER_LOGIN_PATH;
        }

        try {
            //2. 提交认证请求.
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(backendCode,backendPassword));
            //3. 成功: 后台管理的首页.
            return REDIRECT + BACKEND_APP_INDEX_PATH;
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            e.printStackTrace();
            //4. 失败: 重定向到登录页面.
            redirectAttributes.addAttribute("loginInfo","用户名或密码错误..");
            return REDIRECT + BACKEND_USER_LOGIN_PATH;
        }
    }














}
