package com.linqibin.statsservice.schedual;

import com.linqibin.statsservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class UpdateData {
    private StatisticsService statsService;

    @Autowired
    public void setStatsService(StatisticsService statsService) {
        this.statsService = statsService;
    }

    /**
     * 每天0点自动更新昨天的数据统计
     *
     * @param
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/11/11 23:26
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void updateStatistics() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date before = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String date = sdf.format(before);    //格式化前一天
        statsService.createStatisticsByDate(date);
    }
}
