package com.qf.app.config;

import com.qf.app.properties.ApkProperties;
import com.qf.app.properties.PicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理APK无法实时访问问题.
 * @author 郑大仙丶
 * @date 2019-06-14 14:03
 */
@Configuration
public class ApkConfig implements WebMvcConfigurer {

    @Autowired
    private ApkProperties apkProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(apkProperties.getResourceHandler())
                .addResourceLocations(apkProperties.getResourceLocations());

    }
}
