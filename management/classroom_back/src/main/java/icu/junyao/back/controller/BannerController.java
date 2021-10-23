package icu.junyao.back.controller;


import icu.junyao.back.req.BannerEditReq;
import icu.junyao.back.req.BannerReq;
import icu.junyao.back.req.PageBannerReq;
import icu.junyao.back.res.BannerRes;
import icu.junyao.back.service.BannerService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/banner-management")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @ApiOperation("分页条件获取banner")
    @GetMapping("page")
    public R<PageResult<BannerRes>> pageBanner(@Valid PageBannerReq pageBannerReq) {
        return R.data(bannerService.pageBanner(pageBannerReq));
    }

    @ApiOperation("新增banner")
    @PostMapping
    public R<Void> saveBanner(@RequestBody BannerReq bannerReq) {
        bannerService.saveBanner(bannerReq);
        return R.success();
    }

    @ApiOperation("删除单个banner")
    @DeleteMapping("{id}")
    public R<Void> removeBanner(@PathVariable String id) {
        bannerService.removeById(id);
        return R.success();
    }

    @ApiOperation("批量删banner")
    @DeleteMapping("batch")
    public R<Void> removeBanner(@RequestBody List<String> idList) {
        bannerService.removeByIds(idList);
        return R.success();
    }

    @ApiOperation("修改banner")
    @PutMapping("page")
    public R<Void> updateBanner(@RequestBody @Valid BannerEditReq bannerEditReq) {
        bannerService.updateBanner(bannerEditReq);
        return R.success();
    }

    @ApiOperation("获取单个banner详情")
    @GetMapping("{id}")
    public R<BannerRes> bannerDetails(@PathVariable String id) {
        return R.data(bannerService.bannerDetails(id));
    }
}

