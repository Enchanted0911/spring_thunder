package icu.junyao.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.StatisticsDaily;
import icu.junyao.back.req.StatisticDailyReq;
import icu.junyao.back.res.StatisticDailyRes;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计某一天的用户注册人数
     *
     * @param date 待统计的日期
     */
    void registerCount(String date);

    /**
     * 展示统计的数据
     *
     * @param statisticDailyReq {@link StatisticDailyReq}
     * @return {@link StatisticDailyRes}
     */
    StatisticDailyRes showData(StatisticDailyReq statisticDailyReq);
}
