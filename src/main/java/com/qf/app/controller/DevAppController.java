package com.qf.app.controller;

import static com.qf.app.constant.AppConstant.*;

import com.qf.app.bean.AppCategory;
import com.qf.app.bean.AppInfo;
import com.qf.app.bean.AppVersion;
import com.qf.app.bean.DataDictionary;
import com.qf.app.enums.TypeCodeEnum;
import com.qf.app.exception.AppException;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.service.AppCategoryService;
import com.qf.app.service.AppInfoService;
import com.qf.app.service.AppVersionService;
import com.qf.app.service.DataDictionaryService;
import com.qf.app.view.AppMaintain;
import com.qf.app.vo.LayUITableVO;
import com.qf.app.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @Autowired
    private AppVersionService appVersionService;

//    Request URL: http://localhost/dev/app/app-version-add/1
//    Request Method: GET
    @GetMapping("/app-version-add/{id}")
    public String toAppVersonAddPage(@PathVariable Long id,Model model){
        //1. 根据App的最新的三个历史版本
        List<AppVersion> appVersionList = appVersionService.findNewVersionThree(id);
        model.addAttribute("appVersionList",appVersionList);

        // 转发到新增APP版本页面.
        return DEV_APP_VERSION_ADD_PAGE;
    }













    /**
     * 执行添加APP基础信息
     * @param appInfo
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/app-info-add")
    @ResponseBody
    public ResultVO appInfoAdd(@Valid AppInfo appInfo, BindingResult bindingResult,
                               HttpSession session){
        //1. 验证参数.
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            // 响应错误信息.
            return new ResultVO(1,msg);
        }

        try {
            //2. 模拟去session中获取用户信息 id -> devId
            Long devId = 1L;
            appInfo.setDevId(devId);
            //3. 调用service保存数据.
            appInfoService.appInfoAdd(appInfo);
            //4. 响应结果.
            return new ResultVO();
        } catch (AppException e) {
            e.printStackTrace();
            //5. 出现错误,响应错误结果
            return new ResultVO(1,e.getMessage());
        }
    }


    /**
     * 跳转到添加APP基础信息的页面
     * @return
     */
    @GetMapping("/app-info-add")
    public String toAppInfoAddPage(Model model){

        //1. 查询全部的APP所属平台
        List<DataDictionary> appFlatformList =
                dataDictionaryService.findByTypeCode(TypeCodeEnum.APP_FLATFORM.getTypeCode());
        model.addAttribute("appFlatformList",appFlatformList);
        //2. 查询一级分类数据.
        List<AppCategory> categoryLevelOneList = appCategoryService.findByParentId(PARENT_ID);
        model.addAttribute("categoryLevelOneList",categoryLevelOneList);

        // 转发到添加页面.
        return DEV_APP_ADD_PAGE;
    }



    /**
     * 为layUI的table提供json数据
     * @param AppInfoMaintainForm
     * @return
     */
    @GetMapping("/app-info-maintain")
    @ResponseBody
    public LayUITableVO<AppMaintain> appInfoMaintain(AppInfoMaintainForm AppInfoMaintainForm){
        //1. 调用servie查询数据.
        LayUITableVO<AppMaintain> vo = appInfoService.findByCondition(AppInfoMaintainForm);
        //2. 相应数据.
        return vo;
    }




    /**
     *  三级联动,查询对应的分类数据
     * @param parentId
     * @return
     */
    @GetMapping("/find-category")
    @ResponseBody
    public List<AppCategory> findCategory(@RequestParam Long parentId){
        // 查询对应的分类数据.
        List<AppCategory> appCategoryList = appCategoryService.findByParentId(parentId);
        // 响应数据.
        return appCategoryList;
    }


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
