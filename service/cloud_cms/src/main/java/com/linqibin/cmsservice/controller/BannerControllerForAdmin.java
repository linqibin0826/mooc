package com.linqibin.cmsservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linqibin.cmsservice.entity.Banner;
import com.linqibin.cmsservice.service.BannerService;
import com.linqibin.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/cmsservice/banner")
public class BannerControllerForAdmin {
    private BannerService bannerService;

    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable("page") Long page,
                             @PathVariable("limit") Long limit) {
        IPage<Banner> pageBanner = bannerService.pageBanner(page, limit);
        return Result.ok().data("items", pageBanner);
    }

    @PostMapping("/addBanners")
    public Result addBanners(@RequestBody List<Banner> banners) {
        bannerService.saveBanners(banners);
        return Result.ok();
    }

    @DeleteMapping("/deleteBanners")
    public Result deleteBanner(@RequestBody List<String> bannerIds) {
        bannerService.deleteBanners(bannerIds);
        return Result.ok();
    }
}

