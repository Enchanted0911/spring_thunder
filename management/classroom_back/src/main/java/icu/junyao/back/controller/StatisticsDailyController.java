package icu.junyao.back.controller;


import icu.junyao.back.req.StatisticDailyReq;
import icu.junyao.back.res.StatisticDailyRes;
import icu.junyao.back.service.StatisticsDailyService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "每日统计管理")
@RestController
@RequestMapping("/statistics-daily")
@RequiredArgsConstructor
public class StatisticsDailyController {

    private final StatisticsDailyService statisticsDailyService;

    @ApiOperation("统计某一天用户注册人数")
    @PostMapping("register-num/{date}")
    public R<Void> registerCount(@PathVariable String date) {
        statisticsDailyService.registerCount(date);
        return R.success();
    }

    @ApiOperation("统计某一天用户注册人数")
    @GetMapping
    public R<StatisticDailyRes> showData(@Valid StatisticDailyReq statisticDailyReq) {
        return R.data(statisticsDailyService.showData(statisticDailyReq));
    }
}

