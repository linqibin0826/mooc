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
 * @author Hugh &you
 * @since 2020 -10-27
 */
public interface BannerService extends IService<Banner> {

    /**
     * Page banner page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the page
     * @author hugh &you
     * @since 2020 /12/18 12:08
     */
    IPage<Banner> pageBanner(Long page, Long limit);

    /**
     * Save banners.
     *
     * @param banners the banners
     * @author hugh &you
     * @since 2020 /12/18 12:08
     */
    void saveBanners(List<Banner> banners);

    /**
     * Delete banners.
     *
     * @param banners the banners
     * @author hugh &you
     * @since 2020 /12/18 12:08
     */
    void deleteBanners(List<String> banners);

    /**
     * Gets four banners.
     *
     * @return the four banners
     * @author hugh &you
     * @since 2020 /12/18 12:09
     */
    List<Banner> getFourBanners();
}
