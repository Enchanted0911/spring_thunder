package icu.junyao.classroom.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.req.ArticleCommentReq;
import icu.junyao.classroom.req.PageArticleCommentReq;
import icu.junyao.classroom.res.ArticleCommentRes;
import icu.junyao.classroom.service.ArticleCommentService;
import icu.junyao.classroom.util.JwtUtil;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;

    @ApiOperation("分页获取评论")
    @GetMapping("page")
    public R<PageResult<ArticleCommentRes>> pageArticleComment(@Valid PageArticleCommentReq pageArticleCommentReq) {
        return R.data(articleCommentService.pageArticleComment(pageArticleCommentReq));
    }

    @ApiOperation("添加评论")
    @PostMapping
    public R<Void> saveComment(@RequestBody ArticleCommentReq articleCommentReq, HttpServletRequest request) {
        DecodedJWT decodedJwt = jwtUtil.validateToken(request);
        articleCommentService.saveComment(articleCommentReq, decodedJwt);
        return R.success();
    }
}
