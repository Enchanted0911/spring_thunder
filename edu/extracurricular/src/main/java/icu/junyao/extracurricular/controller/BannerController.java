package icu.junyao.extracurricular.controller;


import icu.junyao.common.entity.R;
import icu.junyao.extracurricular.entity.Banner;
import icu.junyao.extracurricular.res.BannerRes;
import icu.junyao.extracurricular.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "banner管理")
@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @ApiOperation("获取所有banner")
    @GetMapping
    public R<List<BannerRes>> gainAllBanner() {
        return R.data(bannerService.gainAllBanner());
    }
}

