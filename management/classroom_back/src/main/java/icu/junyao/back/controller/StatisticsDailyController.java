package icu.junyao.back.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}

