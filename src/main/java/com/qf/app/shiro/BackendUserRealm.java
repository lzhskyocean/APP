package com.qf.app.shiro;

import com.qf.app.bean.BackendUser;
import com.qf.app.service.BackendUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 后台管理用户的realm.
 * @author 郑大仙丶
 * @date 2019-06-17 09:46
 */
@Component
public class BackendUserRealm extends AuthorizingRealm{

    @Autowired
    private BackendUserService backendUserService;


    {
        //1. 创建加密对象Matcher,并设置加密方式和加密次数.
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1024);
        //2. 将matcher设置到当前对象
        this.setCredentialsMatcher(matcher);
    }


    @Override
    public String getName() {
        return "BackendUserRealm";
    }

    // 认证.
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
                AuthenticationToken token) throws AuthenticationException {
        //1. 获取用户名.
        String backendCode = (String) token.getPrincipal();
        //2. 根据用户名查询用户信息.
        BackendUser backendUser = backendUserService.findByBackendCode(backendCode);
        //3. 判断是否为null.   UnkownAccount
        if(backendUser == null){
            return null;
        }
        //4. 创建AuthenticationInfo,并设置正确的用户名和密码,以及当前realm的名字
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(
                        backendUser.getBackendCode(),
                        backendUser.getBackendPassword(),
                        this.getName());
        //5. 设置用户的盐.
        info.setCredentialsSalt(ByteSource.Util.bytes(backendUser.getBackendSalt()));
        //6. 返回封装好的info对象
        return info;
    }


    // 授权.
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 获取用户名.
        String backendCode = (String) principalCollection.getPrimaryPrincipal();
        //2. 根据用户名查询角色信息.
        Set<String> roles = backendUserService.findRolesByBackendCode(backendCode);
        //3. 根据角色信息,查询权限.
        Set<String> perms = backendUserService.findPermsByRoles(roles);
        //4. 封装info对象,并设置查询到的角色和权限信息.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        //5. 返回.
        return info;
    }


}
