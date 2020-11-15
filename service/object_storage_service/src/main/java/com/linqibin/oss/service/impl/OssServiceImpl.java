package com.linqibin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.linqibin.oss.service.OssService;
import com.linqibin.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile multipartFile) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyID = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        //创建上传文件流
        try {
            //新建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);

            //获取上传的头像的文件输入流
            InputStream inputStream = multipartFile.getInputStream();

            //获取文件名称
            String originalFilename = multipartFile.getOriginalFilename();

            //解决用户上传相同文件名的文件时, 避免前面的文件被覆盖,添加一个唯一值
            originalFilename = UUID.randomUUID().toString().replaceAll("-", "") + originalFilename;

            //文件分类(按照日期进行分类
            String dateTimeString = new DateTime().toString("yyyy/MM/dd");


            //最终文件名称,存放到阿里云OSS中的文件
            String finalFilename = dateTimeString + "/" + originalFilename;

            //调用OSS实例中的方法实现上传
            //第二个参数为上传到oss文件路径和文件名称
            ossClient.putObject(bucketName, finalFilename, inputStream);

            //关闭OSSClient
            ossClient.shutdown();

            //返回url
            return "https://" + bucketName + "." + endpoint + "/" + dateTimeString + "/" + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
