package com.linqibin.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.cmsservice.entity.Banner;
import com.linqibin.cmsservice.mapper.BannerMapper;
import com.linqibin.cmsservice.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-27
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public IPage<Banner> pageBanner(Long page, Long limit) {
        Page<Banner> bannerPage = new Page<>(page, limit);
        IPage<Banner> page1 = baseMapper.selectPage(bannerPage, null);
        return page1;
    }

    @Override
    public void saveBanners(List<Banner> banners) {
        for (Banner banner : banners) {
            baseMapper.insert(banner);
        }
    }

    @Override
    public void deleteBanners(List<String> bannerIds) {
        for (String bannerId : bannerIds) {
            baseMapper.deleteById(bannerId);
        }
    }

    @Override
    public List<Banner> getFourBanners() {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.last("limit 4");
        List<Banner> fourBanners = baseMapper.selectList(wrapper);
        return fourBanners;
    }
}
