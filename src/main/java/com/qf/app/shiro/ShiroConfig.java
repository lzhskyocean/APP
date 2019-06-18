package com.qf.app.shiro;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro的配置文件
 * @author 郑大仙丶
 * @date 2019-06-17 10:12
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private BackendUserRealm realm;

    //1. 安全管理器.
    @Bean
    public DefaultWebSecurityManager securityManager(){
        //1. 创建安全管理器.
        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        //2. 设置realm
        securityManager.setRealm(realm);
        //3. 返回
        return securityManager;
    }

    //2. 过滤器链.
    @Bean
    public ShiroFilterChainDefinition chainDefinition(){
        //1. 创建过滤器链对象
        DefaultShiroFilterChainDefinition chainDefinition =
                new DefaultShiroFilterChainDefinition();
        //2. 设置退出登录的路径.
        chainDefinition.addPathDefinition("/backend/user/logout","logout");
        //3. 设置放行的路径.
        chainDefinition.addPathDefinition("/backend/user/**","anon");
        //4. 设置拦截的路径
        chainDefinition.addPathDefinition("/backend/**","authc");
        //5. 返回.
        return chainDefinition;
    }

}
