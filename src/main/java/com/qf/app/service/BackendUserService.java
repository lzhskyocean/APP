package com.qf.app.service;

import com.qf.app.bean.BackendUser;

import java.util.Set;

/**
 * 后台管理的用户service接口
 * @author 郑大仙丶
 * @date 2019-06-17 10:23
 */
public interface BackendUserService {
    // 根据用户名查询用户信息
    BackendUser findByBackendCode(String backendCode);

    // 根据用户性查询对应的角色
    Set<String> findRolesByBackendCode(String backendCode);

    // 根据全部的角色查询对应的权限
    Set<String> findPermsByRoles(Set<String> roles);
}
