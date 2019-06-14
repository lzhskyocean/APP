package com.qf.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 图片的配置信息
 * @author 郑大仙丶
 * @date 2019-06-14 11:19
 */
@Component
@ConfigurationProperties(prefix = "pic")
@Data
public class PicProperties {

    // 图片最大值
    private Long size;
    // 图片允许的类型
    private String types;
    // 图片保存的绝对路径
    private String realPath;
    // 图片的访问路径
    private String path;
    // 图片的访问路径
    private String resourceHandler;
    // 图片的本地绝对路径
    private String resourceLocations;









}
