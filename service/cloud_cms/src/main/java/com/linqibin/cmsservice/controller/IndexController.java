package com.linqibin.cmsservice.controller;

import com.linqibin.cmsservice.entity.Banner;
import com.linqibin.cmsservice.service.IndexService;
import com.linqibin.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页内容
 *
 * @param
 * @author linqibin&youmei
 * @return
 * @creed: ProjectForSDDM
 * @date 2020/10/28 20:25
 */
@RestController
@RequestMapping("/cmsservice/index")
public class IndexController {
    private IndexService indexService;

    @Autowired
    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }


    @GetMapping("/getBanners")
    public Result getBanners() {
        List<Banner> fourBanners = indexService.getBanners();
        return Result.ok().data("items", fourBanners);
    }


}
