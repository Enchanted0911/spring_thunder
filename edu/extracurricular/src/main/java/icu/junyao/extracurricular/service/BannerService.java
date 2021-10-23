package icu.junyao.extracurricular.service;

import icu.junyao.extracurricular.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.extracurricular.res.BannerRes;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface BannerService extends IService<Banner> {

    /**
     * 获取所有banner, 优先级最高的五个
     *
     * @return 所有banner的集合
     */
    List<BannerRes> gainAllBanner();
}
