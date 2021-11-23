package icu.junyao.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.back.client.UserClient;
import icu.junyao.back.constant.StatisticDailyConstants;
import icu.junyao.back.entity.StatisticsDaily;
import icu.junyao.back.mapper.StatisticsDailyMapper;
import icu.junyao.back.req.StatisticDailyReq;
import icu.junyao.back.res.StatisticDailyRes;
import icu.junyao.back.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.R;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private final UserClient userClient;

    @Override
    public void registerCount(String date) {

        // 远程调用 获取当天注册人数
        R<Integer> registerCountRes = userClient.statisticRegister(date);
        Integer registerCountNum = registerCountRes.getData();

        // 如果该日期被统计过则修改记录, 否则新建一条记录
        LambdaQueryWrapper<StatisticsDaily> statisticsDailyLambdaQueryWrapper = Wrappers.lambdaQuery();
        statisticsDailyLambdaQueryWrapper.eq(StatisticsDaily::getDateCalculated, date);
        StatisticsDaily one = super.getOne(statisticsDailyLambdaQueryWrapper);

        // 若存在记录
        if (one != null) {
            one.setRegisterNum(registerCountNum);
            super.updateById(one);
            return;
        }

        // 若不存在记录
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(registerCountNum);

        super.save(statisticsDaily);
    }

    @Override
    public StatisticDailyRes showData(StatisticDailyReq statisticDailyReq) {
        LambdaQueryWrapper<StatisticsDaily> statisticsDailyLambdaQueryWrapper = Wrappers.lambdaQuery();
        statisticsDailyLambdaQueryWrapper
                .between(StatisticsDaily::getDateCalculated, statisticDailyReq.getBegin(), statisticDailyReq.getEnd())
                .orderByDesc(StatisticsDaily::getDateCalculated);

        List<StatisticsDaily> statisticsDailyList = super.list(statisticsDailyLambdaQueryWrapper);

        StatisticDailyRes statisticDailyRes = new StatisticDailyRes();

        statisticsDailyList.forEach(s -> {
            statisticDailyRes.getDateCalculatedList().add(s.getDateCalculated());
            switch (statisticDailyReq.getType()) {
                case StatisticDailyConstants.LOGIN_NUM:
                    statisticDailyRes.getNumDataList().add(s.getLoginNum());
                    break;
                case StatisticDailyConstants.REGISTER_NUM:
                    statisticDailyRes.getNumDataList().add(s.getRegisterNum());
                    break;
                case StatisticDailyConstants.VIDEO_VIEW_NUM:
                    statisticDailyRes.getNumDataList().add(s.getVideoViewNum());
                    break;
                case StatisticDailyConstants.COURSE_NUM:
                    statisticDailyRes.getNumDataList().add(s.getCourseNum());
                    break;
                default:
                    break;
            }
        });

        return statisticDailyRes;
    }
}
