package com.linqibin.cmsservice.service;

import com.linqibin.cmsservice.entity.Banner;

import java.util.List;

public interface IndexService {
    /**
     * Gets banners.
     *
     * @return the banners
     * @author hugh &you
     * @since 2020 /12/18 12:10
     */
    List<Banner> getBanners();
}
