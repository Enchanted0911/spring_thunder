package icu.junyao.classroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.entity.Article;
import icu.junyao.classroom.req.PageArticleReq;
import icu.junyao.classroom.res.ArticleRes;
import icu.junyao.common.entity.PageResult;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
public interface ArticleService extends IService<Article> {
    /**
     * 分页条件查询文章
     *
     * @param pageArticleReq {@link PageArticleReq}
     * @return {@link ArticleRes}
     */
    PageResult<ArticleRes> pageArticle(PageArticleReq pageArticleReq);

    /**
     * 获取文章详情
     *
     * @param id 文章id
     * @return {@link ArticleRes}
     */
    ArticleRes articleDetails(String id);
}
