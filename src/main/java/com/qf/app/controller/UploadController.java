package com.qf.app.controller;

import com.qf.app.properties.ApkProperties;
import com.qf.app.properties.PicProperties;
import com.qf.app.vo.UploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 上传图片.
 * @author 郑大仙丶
 * @date 2019-06-14 11:11
 */
@RestController
public class UploadController {

    @Autowired
    private ApkProperties apkProperties;

    @Autowired
    private PicProperties picProperties;

    /**
     * 接收APK文件
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload-apk")
    public UploadVO uploadApk(MultipartFile file) throws IOException {
        //1. 非空校验.
        if(file == null){
            // 未接收到apk文件.
            return new UploadVO(1,"未接收到apk文件.");
        }
        //2. 校验后缀是否为.apk
        if(!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),apkProperties.getType())){
            // 文件类型不正确,需要apk文件.
            return new UploadVO(1,"文件格式不正确,必须是apk文件.");
        }
        //3. 上传APK文件.
        File realPath = new File(apkProperties.getRealPath() + file.getOriginalFilename());
        if(!realPath.getParentFile().exists()){
            // 如果存放apk文件的目录不存在,创建目录
            realPath.getParentFile().mkdirs();
        }
        file.transferTo(realPath);
        //4. 封装数据  -> apkFileName,downloadLink
        String apkFileName = file.getOriginalFilename();
        String downloadLink = apkProperties.getPath() + apkFileName;
        Map<String,Object> data = new HashMap<>();
        data.put("apkFileName",apkFileName);
        data.put("downloadLink",downloadLink);
        //5. 响应数据.
        return new UploadVO(data);
    }



    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
//    Request URL: http://localhost/upload-pic
//    Request Method: POST
//    file: (binary)
    @PostMapping("/upload-pic")
    public UploadVO uploadPic(MultipartFile file) throws IOException {
        //0. 校验非空.
        if(file == null){
            return new UploadVO(1,"未接收到图片数据!");
        }
        String fileName = file.getOriginalFilename();
        //1. 校验图片大小.
        if(file.getSize() > picProperties.getSize()){
            // 图片大小超出范围
            return new UploadVO(1,"图片大小超出范围,规定为5M!");
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
            return new UploadVO(1,"图片类型错误,允许为" + picProperties.getTypes());
        }

        //3. 校验图片是否损坏.
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image == null){
            // 图片已经损坏
            return new UploadVO(1,"图片已经损坏");
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
        UploadVO vo = new UploadVO();
        // 封装图片的访问路径.
        String picPath = picProperties.getPath() + newName;
        vo.setData(picPath);
        return vo;
    }
}
