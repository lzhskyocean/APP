package com.qf.app.controller;

import static com.qf.app.constant.AppConstant.*;

import com.qf.app.bean.DevUser;
import com.qf.app.exception.AppException;
import com.qf.app.service.DevUserService;
import com.qf.app.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 开发者用户模块的controller
 * @author 郑大仙丶
 * @date 2019-06-11 11:36
 */
@Controller
@RequestMapping("/dev/user")
public class DevUserController {

    @Autowired
    private DevUserService devUserService;


    private final String REGISTER_INFO = "registerInfo";


    @GetMapping("/username-verify")
    @ResponseBody
    public ResultVO usernameVerify(@RequestParam String devUsername){
        //1. 调用service查询
        devUserService.devUserService(devUsername);
        //2. 响应数据
        return new ResultVO();
    }


    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/register")
    public String toRegisterPage(){
        // 转发到页面
        return DEV_USER_REGISTER_PAGE;
    }


    /**
     *     执行注册功能.
     *     Request URL: http://localhost/dev/user/register
     *     Request Method: POST
     *
     *
          	 devUsername: zhangsan
             devPassword: 123123
             devEmail: 123@123.com
             devBirthday: 2019-06-04
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid DevUser devUser, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        //1. 判断参数合法性.
        if(bindingResult.hasErrors()){
            // 获取参数不合法信息
            String registerInfo = bindingResult.getFieldError().getDefaultMessage();
            // 设置错误信息
            redirectAttributes.addAttribute(REGISTER_INFO,registerInfo);
            // 跳转到注册页面.
            return REDIRECT +  DEV_USER_REGISTER_PATH;
        }
        try {
            //2. 调用service完成注册.
            devUserService.register(devUser);
            //3. 注册成功,跳转到登录页面..
            redirectAttributes.addAttribute(REGISTER_INFO,"注册成功,请去指定邮箱激活账号后,登录!");
            return REDIRECT + DEV_USER_LOGIN_PATH;
        } catch (AppException e) {
            e.printStackTrace();
            //4. 重定向到注册页面,并给予提示.
            // 设置错误信息
            redirectAttributes.addAttribute(REGISTER_INFO,"注册账号失败,请稍后再试!!");
            // 跳转到注册页面.
            return REDIRECT +  DEV_USER_REGISTER_PATH;
        }catch (MessagingException e) {
            e.printStackTrace();
            //4. 重定向到注册页面,并给予提示.
            // 发送邮件失败
            redirectAttributes.addAttribute(REGISTER_INFO,"发送邮件失败!");
            // 跳转到注册页面.
            return REDIRECT +  DEV_USER_REGISTER_PATH;
        }
    }


    @GetMapping("/active")
    public String active(@RequestParam String devUsername,
                         @RequestParam String code,
                         Model model){
        try {
            //1. 调用service,完成激活
            devUserService.active(devUsername,code);
            //2. 如果成功,跳转到success页面
            model.addAttribute("success","激活账号成功,<a color='green' href='/dev/user/login'>请登录</a>!");
            return "success";
        } catch (AppException e) {
            e.printStackTrace();
            //3. 如果出现异常,跳转到error界面.
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }


    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String toLoginPage(){
        return DEV_USER_LOGIN_PAGE;
    }


    /**
     * 执行登录
     * @return
     */
    @PostMapping("/login")
    public String login(String devUsername, String devPassword,
                        RedirectAttributes redirectAttributes,
                        HttpSession session){
        //1. 判断参数是否合法.
        if(StringUtils.isEmpty(devUsername) || StringUtils.isEmpty(devPassword)){
            // 给出提示信息
            redirectAttributes.addAttribute("loginInfo","用户名和密码为必填项!");
            // 重定向到登录页面路径
            return REDIRECT + DEV_USER_LOGIN_PATH;
        }
        try {
            //2. 调用service执行登录操作.
            DevUser devUser = devUserService.login(devUsername,devPassword);
            //3. 将用户的信息放到session域中.
            session.setAttribute(DEV_USER_SESSION_KEY,devUser);
            //4. 跳转到APP开发者平台的首页
            return REDIRECT + DEV_APP_INDEX_PATH;
        } catch (AppException e) {
            e.printStackTrace();
            // 给出提示信息
            redirectAttributes.addAttribute("loginInfo",e.getMessage());
            // 重定向到登录页面路径
            return REDIRECT + DEV_USER_LOGIN_PATH;

        }
    }









}
