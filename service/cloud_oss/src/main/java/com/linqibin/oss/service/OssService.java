package com.linqibin.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    /**
     * 头像上传到阿里云的OSS中,  并把图像的url地址返回
     *
     * @param multipartFile
     * @return java.lang.String
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/19 14:53
     */
    String uploadAvatar(MultipartFile multipartFile);
}
