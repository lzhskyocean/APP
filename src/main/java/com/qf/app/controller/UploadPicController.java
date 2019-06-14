package com.qf.app.controller;

import com.qf.app.properties.PicProperties;
import com.qf.app.vo.UploadPicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传图片.
 * @author 郑大仙丶
 * @date 2019-06-14 11:11
 */
@RestController
public class UploadPicController {

    @Autowired
    private PicProperties picProperties;


//    Request URL: http://localhost/upload-pic
//    Request Method: POST
//    file: (binary)
    @PostMapping("/upload-pic")
    public UploadPicVO uploadPic(MultipartFile file, HttpServletRequest request) throws IOException {
        //0. 校验非空.
        if(file == null){
            return new UploadPicVO(1,"未接收到图片数据!");
        }
        String fileName = file.getOriginalFilename();
        //1. 校验图片大小.
        if(file.getSize() > picProperties.getSize()){
            // 图片大小超出范围
            return new UploadPicVO(1,"图片大小超出范围,规定为5M!");
        }

        //2. 校验图片类型.
        //2.1 声明标识符
        boolean flag = false;
        //2.2 获取允许的图片后缀.
        String[] types = picProperties.getTypes().split(",");
        //2.3 遍历types
        for (String type : types) {
            //2.4 比对上传的图片名称后缀是否匹配.
            if(StringUtils.endsWithIgnoreCase(fileName,type)){
                //2.5 图片类型正确.
                flag = true;
                break;
            }
        }
        //2.6 判断
        if(!flag){
            // 图片类型错误
            return new UploadPicVO(1,"图片类型错误,允许为" + picProperties.getTypes());
        }

        //3. 校验图片是否损坏.
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image == null){
            // 图片已经损坏
            return new UploadPicVO(1,"图片已经损坏");
        }

        //4. 给图片起名字.
//        String prefixName = UUID.randomUUID().toString();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

        //5. 将图片保存到本地路径.
        //5.1 声明保存图片的路径
        File realPath = new File(picProperties.getRealPath() + newName);
        //5.2 如果图片路径不存在,创建路径.
        if(!realPath.getParentFile().exists()){
            //5.3 图片的保存目录不存在.
            realPath.getParentFile().mkdirs();
        }
        //5.4 保存图片
        file.transferTo(realPath);
        //6. 响应数据,并携带图片访问路径.
        UploadPicVO vo = new UploadPicVO();
        // 封装图片的访问路径.
        String picPath = picProperties.getPath() + newName;
        vo.setPicPath(picPath);
        return vo;
    }
}
