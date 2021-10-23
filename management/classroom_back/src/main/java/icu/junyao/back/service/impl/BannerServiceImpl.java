package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.back.entity.Banner;
import icu.junyao.back.mapper.BannerMapper;
import icu.junyao.back.req.BannerEditReq;
import icu.junyao.back.req.BannerReq;
import icu.junyao.back.req.PageBannerReq;
import icu.junyao.back.res.BannerRes;
import icu.junyao.back.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public PageResult<BannerRes> pageBanner(PageBannerReq pageBannerReq) {
        // 构建分页和模糊条件
        IPage<Banner> bannerPage = new Page<>(pageBannerReq.getPage(), pageBannerReq.getPageSize());
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = Wrappers.lambdaQuery();
        bannerLambdaQueryWrapper
                .like(StrUtil.isNotEmpty(pageBannerReq.getTitle()), Banner::getTitle, pageBannerReq.getTitle())
                .like(StrUtil.isNotEmpty(pageBannerReq.getLinkUrl()), Banner::getLinkUrl, pageBannerReq.getLinkUrl())
                .ge(StrUtil.isNotEmpty(pageBannerReq.getStart()), Banner::getCreatedTime, pageBannerReq.getStart())
                .le(StrUtil.isNotEmpty(pageBannerReq.getEnd()), Banner::getCreatedTime, pageBannerReq.getEnd());
        super.page(bannerPage, bannerLambdaQueryWrapper);

        // 属性转移
        List<BannerRes> bannerResList = BeanUtil.copyToList(bannerPage.getRecords(), BannerRes.class, CopyOptions.create());

        return new PageResult<>(bannerPage.getTotal(), bannerResList);
    }

    @Override
    public void saveBanner(BannerReq bannerReq) {
        // 标题去重
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = Wrappers.lambdaQuery();
        bannerLambdaQueryWrapper.eq(Banner::getTitle, bannerReq.getTitle());
        Banner banner = super.getOne(bannerLambdaQueryWrapper);
        if (banner != null) {
            throw BusinessResponseEnum.BANNER_TITLE_USED.newException();
        }

        banner = new Banner();
        BeanUtils.copyProperties(bannerReq, banner);
        super.save(banner);
    }

    @Override
    public void updateBanner(BannerEditReq bannerEditReq) {
        // 标题去重 排除自己
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = Wrappers.lambdaQuery();
        bannerLambdaQueryWrapper.eq(Banner::getTitle, bannerEditReq.getTitle())
                .ne(Banner::getId, bannerEditReq.getId());
        Banner banner = super.getOne(bannerLambdaQueryWrapper);
        if (banner != null) {
            throw BusinessResponseEnum.BANNER_TITLE_USED.newException();
        }

        banner = new Banner();
        BeanUtils.copyProperties(bannerEditReq, banner);
        super.updateById(banner);
    }

    @Override
    public BannerRes bannerDetails(String id) {
        Banner banner = super.getById(id);
        if (banner == null) {
            throw BusinessResponseEnum.BANNER_NOT_EXIST.newException();
        }

        BannerRes bannerRes = new BannerRes();
        BeanUtils.copyProperties(banner, bannerRes);

        return bannerRes;
    }
}
