package com.linqibin.statsservice.service;

import com.linqibin.statsservice.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-11
 */
public interface StatisticsService extends IService<Statistics> {

    void createStatisticsByDate(String date);
}
