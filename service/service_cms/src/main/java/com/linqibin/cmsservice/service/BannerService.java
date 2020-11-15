package com.linqibin.cmsservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.cmsservice.entity.Banner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-27
 */
public interface BannerService extends IService<Banner> {

    IPage<Banner> pageBanner(Long page, Long limit);

    void saveBanners(List<Banner> banners);

    void deleteBanners(List<String> banners);

    List<Banner> getFourBanners();
}
