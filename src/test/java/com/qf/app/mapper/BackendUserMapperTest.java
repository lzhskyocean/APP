package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-17 10:54
 */
public class BackendUserMapperTest extends AppApplicationTests {

    @Autowired
    private BackendUserMapper backendUserMapper;

    @Test
    public void findRolesByBackendCode() {
        Set<String> roles = backendUserMapper.findRolesByBackendCode("admin");

        System.err.println(roles);
    }


    @Test
    public void findPermsByRoles(){
        Set<String> roles = new HashSet<>();
        roles.add("超级管理员");

        Set<String> perms = backendUserMapper.findPermsByRoles(roles);

        System.err.println(perms);
    }
}