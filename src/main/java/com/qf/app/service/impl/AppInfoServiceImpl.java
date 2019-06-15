package com.qf.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.app.bean.AppInfo;
import com.qf.app.enums.AppStatusEnum;
import com.qf.app.exception.AppException;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.mapper.AppInfoMapper;
import com.qf.app.service.AppInfoService;
import com.qf.app.util.GsonUtil;
import com.qf.app.view.AppMaintain;
import com.qf.app.vo.AppDownloadsVO;
import com.qf.app.vo.LayUITableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * APP详细信息的service层.
 * @author 郑大仙丶
 * @date 2019-06-12 11:38
 */
@Service
@Slf4j
public class AppInfoServiceImpl implements AppInfoService {

    // 设置定存放在redis中的下载量排行数据的key
    private final String REDIS_DOWNLOADS_KEY = "devappinfo:downloadsTopFive";

    // 设置生存时间
    private final long REDIS_DOWNLOADS_KEY_EXPIRE = 1L;

    // 软件默认下载量
    private final long DEFAULT_DOWNLOADS = 0L;

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 查询下载量TOP FIVE
     * @return
     */
    @Override
    public String findDownloadsTopFive() {
        System.out.println("准备查询下载量数据: " + System.currentTimeMillis());
        //=========================去redis缓存中查询数据.===============================
        String result = redisTemplate.opsForValue().get(REDIS_DOWNLOADS_KEY);
        if(!StringUtils.isEmpty(result)) {
            //1. 如果查询到数据,直接返回.
            System.out.println("在redis中获取的数据: " + System.currentTimeMillis());
            return result;
        }

        //=========================去数据库查询指定的数据===============================
        //1. 调用appInfoMapper查询.
        List<AppInfo> list = appInfoMapper.findOrderByDownloadsDescLimit();
        //2. 封装数据.
        List<AppDownloadsVO> downloadsVOResult = new ArrayList<>();
        for (AppInfo appInfo : list) {
            AppDownloadsVO vo = new AppDownloadsVO(appInfo.getSoftwareName(),appInfo.getDownloads());
            downloadsVOResult.add(vo);
        }
        //3. 将查询到的数据序列化
        result = GsonUtil.toJson(downloadsVOResult);
        //4. 再次放到redis缓存中.
        redisTemplate.opsForValue().set(REDIS_DOWNLOADS_KEY,result,REDIS_DOWNLOADS_KEY_EXPIRE, TimeUnit.HOURS);
        //========================================================
        //3. 返回.
        System.out.println("在mysql数据库中获取的数据: " + System.currentTimeMillis());
        return result;
    }


    /**
     * app维护页面需要的表格数据
     * @param appInfoMaintainForm
     * @return
     */
    @Override
    public LayUITableVO<AppMaintain> findByCondition(AppInfoMaintainForm appInfoMaintainForm) {
        //1. 分页.
        PageHelper.startPage(appInfoMaintainForm.getPage(),appInfoMaintainForm.getLimit());
        //2. 调用mapper接口查询数据.
        List<AppMaintain> list = appInfoMapper.findByCondition(appInfoMaintainForm);
        //3. 封装PageInfo
        PageInfo pageInfo = new PageInfo<>(list);
        //4. 封装LayUITableVO
        LayUITableVO<AppMaintain> vo =
                new LayUITableVO<>(pageInfo.getTotal(),pageInfo.getList());
        //5. 返回数据
        return vo;
    }


    /**
     * 添加APP基础信息.
     * @param appInfo
     */
    @Override
    @Transactional
    public void appInfoAdd(AppInfo appInfo) {
        //1. 封装数据.
        //1.1 app状态
        appInfo.setAppStatus(AppStatusEnum.WAIT_EXAM.getStatus());
        //1.2 app的下载量
        appInfo.setDownloads(DEFAULT_DOWNLOADS);
        //2. 调用mapper插入数据
        int count = appInfoMapper.insertSelective(appInfo);
        //3. 判断添加是否成功
        if(count != 1){
            // 添加失败
            log.error("[添加APP基础信息] 添加基础信息失败 appInfo = {}",appInfo);
            throw new AppException("添加APP基础信息失败,请联系管理员!");
        }
    }


    // 修改APP版本
    @Override
    @Transactional
    public void updateVersionId(AppInfo appInfo) {
        int count = appInfoMapper.updateByPrimaryKeySelective(appInfo);
        if(count != 1){
            log.error("[添加APP版本] 修改APP版本号失败! appInfo = {}",appInfo);
            throw new AppException("修改APP版本号失败!");
        }
    }


    // 上架操作.
    @Override
    @Transactional
    public void onSale(List<Long> ids) {
        //1. 集合的长度不为0
        if(ids == null || ids.size() == 0){
            log.error("[上架] 未选中任何的软件  ids = {}" ,ids);
            throw new AppException("执行上架,需要选中某一行数据!");
        }
        //2. 根据ids查询软件信息.
        List<AppInfo> list = appInfoMapper.findByIdIn(ids);
        //3. 判断,如果状态不为2或5,代表软件状态不正确.
        for (AppInfo appInfo : list) {
            if(!(appInfo.getAppStatus() == 2 || appInfo.getAppStatus() == 5)){
                // 软件状态不正确.
                log.error("[上架] 软件状态不正确  appInfo = {}" ,appInfo);
                throw new AppException(appInfo.getSoftwareName() + "状态不正确!");
            }
        }
        //4. 执行修改软件状态.
        Map<String, Object> param = new HashMap<>();
        param.put("ids",ids);
        param.put("status",AppStatusEnum.ON_SALE.getStatus());
        Integer count = appInfoMapper.updateAppStatusByIdIn(param);
        if(count != ids.size()){
            log.error("[上架] 修改软件状态异常  param = {}" ,param);
            throw new AppException("修改软件状态异常,请联系管理员!");
        }
    }
}
