package icu.junyao.back.controller;

import icu.junyao.back.req.PageArticleCommentReq;
import icu.junyao.back.req.PageArticleReq;
import icu.junyao.back.res.ArticleCommentRes;
import icu.junyao.back.res.ArticleContentRes;
import icu.junyao.back.res.ArticleRes;
import icu.junyao.back.service.ArticleCommentService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * article_comment表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Api(tags = "文章评论管理")
@RestController
@RequestMapping("/article-comment")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @ApiOperation("分页条件获取评论")
    @GetMapping("page")
    public R<PageResult<ArticleCommentRes>> pageArticleComment(@Valid PageArticleCommentReq pageArticleCommentReq) {
        return R.data(articleCommentService.pageArticleComment(pageArticleCommentReq));
    }


}
