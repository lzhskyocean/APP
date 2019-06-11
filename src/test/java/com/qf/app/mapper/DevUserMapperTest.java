package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.DevUser;
import com.qf.app.enums.DevUserStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 12:14
 */
public class DevUserMapperTest extends AppApplicationTests{

    @Autowired
    private DevUserMapper devUserMapper;

    @Test
    @Transactional
    public void insert(){
        DevUser devUser = new DevUser();
        devUser.setDevUsername("zhangsan");
        devUser.setDevPassword("suifsdiufgefiggybjerb433gbjk");//1.
        devUser.setDevSalt("kj45435-reg33-f43f43-f43f43f");//2.
        devUser.setDevEmail("123@123.com");
        devUser.setDevCode("kj45435-reg33-f43f43-f43f43fkj45435-reg33-f43f43-f43f43f");//3.
        devUser.setDevBirthday(new Date());
        devUser.setDevState(0);//4.

        int count = devUserMapper.insertSelective(devUser);

        assertEquals(1,count);
    }


    // 根据code和devUsername查询用户信息
    @Test
    public void findByDevUsernameAndCode(){
        String devUsername = "zhangsan";
        String code = "bb5c8e8d-87b0-4804-96d1-0e238313cde1252fca63-f26f-4a79-9402-9957c1d8dad7";

        //1. 封装查询条件
        DevUser param = new DevUser();
        param.setDevUsername(devUsername);
        param.setDevCode(code);
        //2. 查询
        DevUser devUser = devUserMapper.selectOne(param);
        if(devUser != null){
            devUser.setDevState(DevUserStateEnum.ACTIVE.getState());
            devUser.setDevCode(null);
            int count = devUserMapper.updateByPrimaryKey(devUser);
        }

    }


    // 根据用户名查询用户信息
    @Test
    public void findByDevUsername(){
        //1. 封装查询条件
        DevUser param = new DevUser();
        param.setDevUsername("zhangsan");
        //2. 执行查询
        DevUser devUser = devUserMapper.selectOne(param);
        System.err.println(devUser);
    }


}