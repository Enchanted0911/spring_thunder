package icu.junyao.extracurricular.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.extracurricular.entity.Banner;
import icu.junyao.extracurricular.mapper.BannerMapper;
import icu.junyao.extracurricular.res.BannerRes;
import icu.junyao.extracurricular.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<BannerRes> gainAllBanner() {
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = Wrappers.lambdaQuery();
        bannerLambdaQueryWrapper.orderByAsc(Banner::getSort).last("limit 5");

        List<Banner> bannerList = super.list(bannerLambdaQueryWrapper);
        List<BannerRes> bannerResList = new ArrayList<>();
        bannerList.forEach(b -> {
            BannerRes bannerRes = new BannerRes();
            BeanUtils.copyProperties(b, bannerRes);
            bannerResList.add(bannerRes);
        });
        return bannerResList;
    }
}
