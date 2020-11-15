package com.linqibin.statsservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.linqibin.commonutils.Result;
import com.linqibin.statsservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/statsservice")
@CrossOrigin
public class StatisticsController {
    private StatisticsService statsService;

    @Autowired
    public void setStatsService(StatisticsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("{date}")
    public Result createStatisticsByDate(@PathVariable String date) {
        statsService.createStatisticsByDate(date);
        return Result.ok();
    }
}

