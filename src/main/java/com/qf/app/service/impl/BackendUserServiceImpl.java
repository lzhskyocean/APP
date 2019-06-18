package com.qf.app.service.impl;

import com.qf.app.bean.BackendUser;
import com.qf.app.mapper.BackendUserMapper;
import com.qf.app.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 后台管理的用户service实现类.
 * @author 郑大仙丶
 * @date 2019-06-17 10:24
 */
@Service
public class BackendUserServiceImpl implements BackendUserService {

    @Autowired
    private BackendUserMapper backendUserMapper;

    @Override
    public BackendUser findByBackendCode(String backendCode) {
        //1. 封装查询条件
        BackendUser param = new BackendUser();
        param.setBackendCode(backendCode);
        //2. 查询.
        BackendUser backendUser = backendUserMapper.selectOne(param);
        //3. 返回
        return backendUser;
    }


    // 查询用户的角色信息
    @Override
    public Set<String> findRolesByBackendCode(String backendCode) {
        //  联合backendUser和dataDictionary查询用户的角色信息
        return backendUserMapper.findRolesByBackendCode(backendCode);
    }

    // 查询用户的权限信息
    @Override
    public Set<String> findPermsByRoles(Set<String> roles) {
        //  联合backendUser和roles_perms查询权限信息.
        return backendUserMapper.findPermsByRoles(roles);
    }
}
