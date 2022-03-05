package icu.junyao.classroom.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.entity.ArticleComment;
import icu.junyao.classroom.req.ArticleCommentReq;
import icu.junyao.classroom.req.PageArticleCommentReq;
import icu.junyao.classroom.res.ArticleCommentRes;
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
     * @return 评论分页列表
     */
    PageResult<ArticleCommentRes> pageArticleComment(PageArticleCommentReq pageArticleCommentReq);

    /**
     * 添加文章评论
     *
     * @param articleCommentReq {@link ArticleCommentReq}
     * @param decodedJwt {@link DecodedJWT}
     */
    void saveComment(ArticleCommentReq articleCommentReq, DecodedJWT decodedJwt);
}
