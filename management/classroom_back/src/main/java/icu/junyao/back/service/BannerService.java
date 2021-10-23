package icu.junyao.back.service;

import icu.junyao.back.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.req.BannerEditReq;
import icu.junyao.back.req.BannerReq;
import icu.junyao.back.req.PageBannerReq;
import icu.junyao.back.res.BannerRes;
import icu.junyao.common.entity.PageResult;

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
     * 分页获取banner, 可按创建时间 标题 url 模糊搜索
     *
     * @param pageBannerReq {@link PageBannerReq}
     * @return 包含当前页内容和总记录数
     */
    PageResult<BannerRes> pageBanner(PageBannerReq pageBannerReq);

    /**
     * 新增一条banner
     *
     * @param bannerReq {@link BannerReq}
     */
    void saveBanner(BannerReq bannerReq);

    /**
     * 修改一条banner
     *
     * @param bannerEditReq {@link BannerEditReq}
     */
    void updateBanner(BannerEditReq bannerEditReq);

    /**
     * 获取一条banner详情
     *
     * @param id 待获取banner的id
     * @return {@link BannerRes}
     */
    BannerRes bannerDetails(String id);
}
