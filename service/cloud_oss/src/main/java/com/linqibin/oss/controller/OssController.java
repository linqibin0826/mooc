package com.linqibin.oss.controller;

import com.linqibin.commonutils.Result;
import com.linqibin.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    private OssService ossService;

    @Autowired
    public void setOssService(OssService ossService) {
        this.ossService = ossService;
    }

    /**
     * @param file
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/19 14:53
     */
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(MultipartFile file) {
        System.out.println(file.getName());
        String url = ossService.uploadAvatar(file);
        return Result.ok().data("url", url);
    }
}
