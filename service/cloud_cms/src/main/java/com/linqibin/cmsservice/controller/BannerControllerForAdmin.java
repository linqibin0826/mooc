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

    /**
     * 轮播图分页查询
     *
     * @param page  the page
     * @param limit the limit
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:06
     */
    @GetMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable("page") Long page,
                             @PathVariable("limit") Long limit) {
        IPage<Banner> pageBanner = bannerService.pageBanner(page, limit);
        return Result.ok().data("items", pageBanner);
    }

    /**
     * 添加轮播图
     *
     * @param banners the banners
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:07
     */
    @PostMapping("/addBanners")
    public Result addBanners(@RequestBody List<Banner> banners) {
        bannerService.saveBanners(banners);
        return Result.ok();
    }

    /**
     * Delete banner result.
     *
     * @param bannerIds the banner ids
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:07
     */
    @DeleteMapping("/deleteBanners")
    public Result deleteBanner(@RequestBody List<String> bannerIds) {
        bannerService.deleteBanners(bannerIds);
        return Result.ok();
    }
}

