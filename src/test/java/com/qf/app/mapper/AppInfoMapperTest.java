package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.AppInfo;
import com.qf.app.enums.AppStatusEnum;
import com.qf.app.view.AppMaintain;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 11:35
 */
public class AppInfoMapperTest extends AppApplicationTests {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Test
    public void findOrderByDownloadsDescLimit() {
        List<AppInfo> list = appInfoMapper.findOrderByDownloadsDescLimit();

        for (AppInfo appInfo : list) {
            System.err.println(appInfo);
        }
    }

    @Test
    public void findByCondition(){
        List<AppMaintain> list = appInfoMapper.findByCondition(null);
        for (AppMaintain appMaintain : list) {
            System.err.println(appMaintain);
        }
    }


    @Test
    public void insert(){
        //1. 封装数据.
        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(16);
        appInfo.setSoftwareName("宠物小精灵");
        appInfo.setOnSaleDate(null);
        appInfo.setOffSaleDate(null);
        appInfo.setCreated(null);
        appInfo.setUpdated(null);
        appInfo.setId(null);
        appInfo.setVersionId(null);

        // service
        appInfo.setAppStatus(1L);
        appInfo.setDownloads(0L);

        //2. 插入到数据库.
        appInfoMapper.insertSelective(appInfo);

    }



    @Test
    @Transactional
    public void updateAppStatusByIdIn(){
        List<Long> ids = Arrays.asList(1L,2L,3L,4L,5L);

        Map<String,Object> param = new HashMap<>();

        param.put("ids",ids);
        param.put("status", AppStatusEnum.ON_SALE.getStatus());

        Integer count = appInfoMapper.updateAppStatusByIdIn(param);

        System.out.println(count);

    }




    @Test
    public void findByIdIn(){
        List<Long> ids = Arrays.asList(1L,2L,3L,4L,5L);

        List<AppInfo> list = appInfoMapper.findByIdIn(ids);

        for (AppInfo appInfo : list) {
            System.err.println(appInfo.getSoftwareName() + ".." + appInfo.getAppStatus());
        }


    }










}