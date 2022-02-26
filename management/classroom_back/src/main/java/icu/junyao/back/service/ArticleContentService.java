package icu.junyao.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.ArticleContent;
import icu.junyao.back.req.ArticleContentEditReq;
import icu.junyao.back.res.ArticleContentRes;

/**
 * <p>
 * 文章内容 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface ArticleContentService extends IService<ArticleContent> {

    /**
     * 获取文章内容详情
     *
     * @param id 文章id
     * @return 文章内容详情
     */
    ArticleContentRes articleContentDetails(String id);

    /**
     * 修改文章内容
     *
     * @param articleContentEditReq {@link ArticleContentEditReq}
     */
    void updateArticleContent(ArticleContentEditReq articleContentEditReq);
}
