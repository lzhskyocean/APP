package com.qf.app.controller;

import static com.qf.app.constant.AppConstant.*;

import com.qf.app.bean.AppCategory;
import com.qf.app.bean.DataDictionary;
import com.qf.app.enums.TypeCodeEnum;
import com.qf.app.service.AppCategoryService;
import com.qf.app.service.AppInfoService;
import com.qf.app.service.DataDictionaryService;
import com.qf.app.vo.AppDownloadsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * 开发者平台的APP模块
 * @author 郑大仙丶
 * @date 2019-06-12 09:16
 */
@Controller
@RequestMapping("/dev/app")
public class DevAppController {

    private final long PARENT_ID = 0L;

    @Autowired
    private AppInfoService appInfoService;


    @Autowired
    private DataDictionaryService dataDictionaryService;


    @Autowired
    private AppCategoryService appCategoryService;

    /**
     * 跳转到app维护页面
     * @return
     */
    @GetMapping("/appMaintain")
    public String appMaintain(Model model){
        //1. 查询全部的APP状态
        List<DataDictionary> appStatusList =
                dataDictionaryService.findByTypeCode(TypeCodeEnum.APP_STATUS.getTypeCode());
        model.addAttribute("appStatusList",appStatusList);
        //2. 查询全部的APP所属平台
        List<DataDictionary> appFlatformList =
                dataDictionaryService.findByTypeCode(TypeCodeEnum.APP_FLATFORM.getTypeCode());
        model.addAttribute("appFlatformList",appFlatformList);
        //3. 查询一级分类数据.
        List<AppCategory> categoryLevelOneList = appCategoryService.findByParentId(PARENT_ID);
        model.addAttribute("categoryLevelOneList",categoryLevelOneList);
        // 转发页面
        return DEV_APP_MAINTAIN_PAGE;
    }


    /**
     * 查询APP下载量的TOP FIVE
     * @return
     */
    @GetMapping("/downloads")
    @ResponseBody
    public String downloads(){
        //1. 调用service查询相应的数据.
        String result = appInfoService.findDownloadsTopFive();
        //2. 响应数据.
        return result;
    }



    /**
     * 跳转到开发者平台APP首页.
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return DEV_APP_INDEX_PAGE;
    }
}
