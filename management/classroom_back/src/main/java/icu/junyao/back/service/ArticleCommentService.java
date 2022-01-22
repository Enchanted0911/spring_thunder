package icu.junyao.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.ArticleComment;
import icu.junyao.back.req.PageArticleCommentReq;
import icu.junyao.back.res.ArticleCommentRes;
import icu.junyao.common.entity.PageResult;

/**
 * <p>
 * 文章评论 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    /**
     * 分页获取评论
     *
     * @param pageArticleCommentReq {@link PageArticleCommentReq}
     * @return 分页后的评论列表
     */
    PageResult<ArticleCommentRes> pageArticleComment(PageArticleCommentReq pageArticleCommentReq);
}
