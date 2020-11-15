package com.linqibin.cmsservice.service.impl;

import com.linqibin.cmsservice.entity.Banner;
import com.linqibin.cmsservice.service.BannerService;
import com.linqibin.cmsservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    private BannerService bannerService;
    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Override
    @Cacheable(value = "banner", key = "'getBanners'")
    public List<Banner> getBanners() {
        List<Banner> fourBanners = bannerService.getFourBanners();
        return fourBanners;
    }
}
