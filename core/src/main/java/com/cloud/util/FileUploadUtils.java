package com.cloud.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileUploadUtils {

    /**
     * 文件上传，返回图片地址
     * @param files
     * @param userId
     * @return
     */
    public static String fileUpload(MultipartFile files,Integer userId){
        return Optional.ofNullable(files).map(x->{

                 if (x != null) {// 判断上传的文件是否为空
                     String path = null;// 文件路径
                     String type = null;// 文件类型
                     String fileName = x.getOriginalFilename();// 文件原名称
                     System.out.println("上传的文件原名称:" + fileName);
                     // 判断文件类型
                     type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                     if (type != null) {// 判断文件类型是否为空
                         if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                             // 项目在容器中实际发布运行的根路径
                             // 自定义的文件名称
                             String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                             File userDir = new File("/"+userId);
                             if(!userDir.exists()){
                                 userDir.mkdir();
                             }
                             // 设置存放图片文件的路径
                             path = "/"+userId +/*System.getProperty("file.separator")+*/trueFileName;
                             //System.out.println("存放图片文件的路径:"+path);
                             // 转存文件到指定的路径
                             try {
                                 x.transferTo(new File(path));
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             // System.out.println("文件成功上传到指定目录下");
                         } else {
                             //System.out.println("不是我们想要的文件类型,请按要求重新上传");
                             throw new IllegalArgumentException("不是我们想要的文件类型,请按要求重新上传");
                         }
                     } else {
                         //System.out.println("文件类型为空");
                         throw new IllegalArgumentException("文件类型为空");
                     }
                    return path;
                 } else {
                     // System.out.println("没有找到相对应的文件");
                     throw new IllegalArgumentException("没有找到相对应的文件");
                 }
        }).orElseThrow(()->new IllegalArgumentException("图片请求异常！"));
    }
}
