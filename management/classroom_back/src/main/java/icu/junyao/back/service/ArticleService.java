package icu.junyao.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.Article;
import icu.junyao.back.req.ArticleEditReq;
import icu.junyao.back.req.ArticleReq;
import icu.junyao.back.req.PageArticleReq;
import icu.junyao.back.res.ArticleRes;
import icu.junyao.common.entity.PageResult;

import java.util.List;

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
     * 新增一篇文章
     *
     * @param articleReq {@link ArticleReq}
     */
    void saveArticle(ArticleReq articleReq);

    /**
     * 修改文章
     *
     * @param articleEditReq {@link ArticleEditReq}
     */
    void updateArticle(ArticleEditReq articleEditReq);

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    void removeArticle(String id);

    /**
     * 批量删除文章
     *
     * @param idList 文章id列表
     */
    void removeArticle(List<String> idList);

    /**
     * 获取文章详情
     *
     * @param id 文章id
     * @return {@link ArticleRes}
     */
    ArticleRes articleDetails(String id);
}
