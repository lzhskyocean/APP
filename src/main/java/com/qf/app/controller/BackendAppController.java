package com.qf.app.controller;

import static com.qf.app.constant.AppConstant.*;

import com.qf.app.bean.AppCategory;
import com.qf.app.bean.AppVersion;
import com.qf.app.bean.DataDictionary;
import com.qf.app.enums.AppStatusEnum;
import com.qf.app.enums.TypeCodeEnum;
import com.qf.app.exception.AppException;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.service.AppCategoryService;
import com.qf.app.service.AppInfoService;
import com.qf.app.service.AppVersionService;
import com.qf.app.service.DataDictionaryService;
import com.qf.app.view.AppMaintain;
import com.qf.app.vo.LayUITableVO;
import com.qf.app.vo.LayUITreeVO;
import com.qf.app.vo.ResultVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 后台管理的APPcontroller
 * @author 郑大仙丶
 * @date 2019-06-17 11:21
 */
@Controller
@RequestMapping("/backend/app")
public class BackendAppController {

    @Autowired
    private AppCategoryService appCategoryService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private AppVersionService appVersionService;


    @PostMapping("/app-exam-pass")
    @ResponseBody
    public ResultVO appExamPass(@RequestParam Long id){
        try {
            //1. 调用service审核通过
            appInfoService.updateAppStatus(id, AppStatusEnum.EXAM_PASS.getStatus());
            return new ResultVO();
        } catch (AppException e) {
            e.printStackTrace();
            return new ResultVO(1,e.getMessage());
        }
    }
    @PostMapping("/app-exam-not-pass")
    @ResponseBody
    public ResultVO appExamNotPass(@RequestParam Long id){
        try {
            //1. 调用service审核通过
            appInfoService.updateAppStatus(id, AppStatusEnum.EXAM_NOT.getStatus());
            return new ResultVO();
        } catch (AppException e) {
            e.printStackTrace();
            return new ResultVO(1,e.getMessage());
        }
    }


    /**
     * 跳转到APP详细信息页面
     * @param id
     * @return
     */
    @GetMapping("/app-info-one")
    public String appInfoOne(@RequestParam Long id,Model model){
        //1. 根据App的最新的三个历史版本
        List<AppVersion> appVersionList = appVersionService.findNewVersionThree(id);
        model.addAttribute("appVersionList",appVersionList);
        //2. 查询APP的详细信息
        AppMaintain appMaintain = appInfoService.findAppMaintainById(id);
        model.addAttribute("appMaintain",appMaintain);
        //3. 返回app-info-one页面
        return BACKEND_APP_INFO_ONE_PAGE;
    }



    /**
     * 为layUI的table提供json数据
     * @param appInfoMaintainForm
     * @return
     */
    @GetMapping("/app-info-all")
    @ResponseBody
    public LayUITableVO<AppMaintain> appInfoMaintain(AppInfoMaintainForm appInfoMaintainForm){
        appInfoMaintainForm.setAppStatusId(1);
        //1. 调用servie查询数据.
        LayUITableVO<AppMaintain> vo = appInfoService.findByCondition(appInfoMaintainForm);
        //2. 相应数据.
        return vo;
    }


    /**
     * 跳转到app展示页面
     * @return
     */
    @GetMapping("/app-info-list")
    public String appMaintain(Model model){
        //2. 查询全部的APP所属平台
        List<DataDictionary> appFlatformList =
                dataDictionaryService.findByTypeCode(TypeCodeEnum.APP_FLATFORM.getTypeCode());
        model.addAttribute("appFlatformList",appFlatformList);
        //3. 查询一级分类数据.
        List<AppCategory> categoryLevelOneList = appCategoryService.findByParentId(PARENT_ID);
        model.addAttribute("categoryLevelOneList",categoryLevelOneList);
        // 转发页面
        return BACKEND_APP_INFO_PAGE;
    }


    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("/app-category-delete")
    @RequiresPermissions("back:category:delete")
    @ResponseBody
    public ResultVO appCategoryDelete(@RequestParam Long id){
        try {
            //1. 调用service删除.
            appCategoryService.delete(id);
            return new ResultVO();
        } catch (AppException e) {
            e.printStackTrace();
            return new ResultVO(1,e.getMessage());
        }

    }

    /**
     * 修改节点名字
     * @param appCategory
     * @param bindingResult
     * @return
     */
    @PostMapping("/app-category-update")
    @ResponseBody
    public ResultVO appCategoryUpdate(@Valid AppCategory appCategory,
                                      BindingResult bindingResult){
        //1. 校验参数
        if(bindingResult.hasErrors()){
            // 参数异常.
            return new ResultVO(1,bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //2. 调用service修改.
            appCategoryService.update(appCategory);
            //3. 响应
            return new ResultVO();
        } catch (AppException e) {
            e.printStackTrace();
            return new ResultVO(1,e.getMessage());
        }
    }



    /**
     * 添加分类
     * @param parentId
     * @return
     */
    @PostMapping("/app-category-add")
    @ResponseBody
    public String appCategoryAdd(@RequestParam("id") Long parentId){
        // 1. 设置默认的分类名称
        String categoryName = "未命名";
        // 2. 封装数据
        AppCategory appCategory = new AppCategory();
        appCategory.setParentId(parentId);
        appCategory.setCategoryName(categoryName);
        // 3. 调用service保存. -> 自增的主键值.
        Long id = appCategoryService.add(appCategory);
        // 4. 响应.
        return id.toString();
    }




    /**
     * 查询全部的分类数据,在layUI的树组件中展示.
     * @return
     */
    @GetMapping("/app-category-all")
    @ResponseBody
    public List<LayUITreeVO> appCategoryAll(){
        //1. 调用service查询全部分类数据.
        List<LayUITreeVO> data = appCategoryService.findAllCategoryForLayUITree();
        //2. 响应数据.
        return data;
    }


    /**
     * 跳转到app分类管理页面
     * @return
     */
    @GetMapping("/app-category-manager")
    public String appCategoryManager(){
        return BACKEND_APP_CATEGORY_PAGE;
    }


    /**
     * 跳转到后台管理首页.
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return BACKEND_APP_INDEX_PAGE;
    }






    // 测试shiro授权功能
    @GetMapping("/perms1")
    @RequiresPermissions({"back:app:exam","back:app:delete"})
    @ResponseBody
    public String perms1(){
        return "success!!";
    }

    // 测试shiro授权功能
    @GetMapping("/perms2")
    @RequiresPermissions({"back:app:exam111","back:app:delete"})
    @ResponseBody
    public String perms2(){
        return "success!!";
    }


}
