package com.linqibin.statsservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.statsservice.entity.Statistics;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-11
 */
public interface StatisticsService extends IService<Statistics> {

    /**
     * Create statistics by date.
     *
     * @param date the date
     * @author hugh &you
     * @since 2020 /12/18 13:14
     */
    void createStatisticsByDate(String date);
}
