package com.qf.app.config;

import com.qf.app.properties.PicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理图片无法实时访问问题.
 * @author 郑大仙丶
 * @date 2019-06-14 14:03
 */
@Configuration
public class PicConfig implements WebMvcConfigurer {

    @Autowired
    private PicProperties picProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将访问路径的img/** 映射到本地的H:\app\src\main\resources\static\img
        registry
                .addResourceHandler(picProperties.getResourceHandler())
                .addResourceLocations(picProperties.getResourceLocations());

    }
}
