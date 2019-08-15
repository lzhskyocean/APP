package com.qf.app.constant;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 11:39
 */
public interface AppConstant {

    // 重定向关键字
    String REDIRECT = "redirect:";

    Long PARENT_ID = 0L;

    // 用登录成功后,放到session中key.
    String DEV_USER_SESSION_KEY = "devuser";


    //开发者用户注册页面
    String DEV_USER_REGISTER_PAGE = "dev/user/register";
    String DEV_USER_REGISTER_PATH = "/dev/user/register";

    //开发者用户登录页面
    String DEV_USER_LOGIN_PAGE = "dev/user/login";
    String DEV_USER_LOGIN_PATH = "/dev/user/login";

    //开发者APP平台首页
    String DEV_APP_INDEX_PAGE = "dev/app/index";
    String DEV_APP_INDEX_PATH = "/dev/app/index";


    // 开发者APP的APP维护页面
    String DEV_APP_MAINTAIN_PAGE = "dev/app/appMaintain";

    String DEV_APP_MAINTAIN_PATH = "/dev/app/appMaintain";

    // 开发者APP的添加APP基础信息页面
    String DEV_APP_ADD_PAGE = "dev/app/app-info-add";


    // 开发者APP的新增APP版本页面
    String DEV_APP_VERSION_ADD_PAGE = "dev/app/app-version-add";


    //=============================================================

    // 后台管理登录页面
    String BACKEND_USER_LOGIN_PAGE = "backend/user/login";

    String BACKEND_USER_LOGIN_PATH = "/backend/user/login";

    // 后台管理的首页
    String BACKEND_APP_INDEX_PAGE = "backend/app/index";

    String BACKEND_APP_INDEX_PATH = "/backend/app/index";

    // 后台管理的APP分类管理页
    String BACKEND_APP_CATEGORY_PAGE = "backend/app/app-category-manager";

    String BACKEND_APP_CATEGORY_PATH = "/backend/app/app-category-manager";


    // 后台管理的APP展示页
    String BACKEND_APP_INFO_PAGE = "backend/app/app-info-list";

    String BACKEND_APP_INFO_PATH = "/backend/app/app-info-list";


    // 后台管理的APP详细信息页
    String BACKEND_APP_INFO_ONE_PAGE = "backend/app/app-info-one";

    String BACKEND_APP_INFO_ONE_PATH = "/backend/app/app-info-one";
}
