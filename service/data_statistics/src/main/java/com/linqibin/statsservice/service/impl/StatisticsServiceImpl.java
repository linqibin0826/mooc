package com.linqibin.statsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.commonutils.Result;
import com.linqibin.statsservice.entity.Statistics;
import com.linqibin.statsservice.feignclient.UserClient;
import com.linqibin.statsservice.mapper.StatisticsMapper;
import com.linqibin.statsservice.service.StatisticsService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-11
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {
    private UserClient userClient;

    @Autowired
    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void createStatisticsByDate(String date) {
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        baseMapper.delete(wrapper);

        Result result = userClient.getRegisterCount(date);
        Integer registerCount = (Integer) result.getData().get("item");
        Integer loginNum = RandomUtils.nextInt(10, 100);
        //loginNum TODO
        Integer videoViewNum = RandomUtils.nextInt(10, 100);
        //videoViewNum TODO
        Integer courseNum = RandomUtils.nextInt(10, 100);
        //courseNum TODO
        Statistics statistics = new Statistics();
        statistics.setDateCalculated(date);
        statistics.setRegisterNum(registerCount);
        statistics.setCourseNum(courseNum);
        statistics.setLoginNum(loginNum);
        statistics.setVideoViewNum(videoViewNum);
        baseMapper.insert(statistics);

    }
}
