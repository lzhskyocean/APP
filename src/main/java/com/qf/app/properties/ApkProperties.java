package com.qf.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * apk文件的配置信息
 * @author 郑大仙丶
 * @date 2019-06-14 11:19
 */
@Component
@ConfigurationProperties(prefix = "apk")
@Data
public class ApkProperties {

    // apk允许的类型
    private String type;
    // apk保存的绝对路径
    private String realPath;
    // apk的访问路径
    private String path;
    // apk的访问路径
    private String resourceHandler;
    // apk的本地绝对路径
    private String resourceLocations;









}
