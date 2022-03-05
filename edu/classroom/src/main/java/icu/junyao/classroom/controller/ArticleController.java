package icu.junyao.classroom.controller;

import icu.junyao.classroom.req.PageArticleReq;
import icu.junyao.classroom.res.ArticleRes;
import icu.junyao.classroom.service.ArticleService;
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
 * article表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation("分页条件获取文章")
    @GetMapping("page")
    public R<PageResult<ArticleRes>> pageArticle(@Valid PageArticleReq pageArticleReq) {
        return R.data(articleService.pageArticle(pageArticleReq));
    }

    @ApiOperation("获取文章详情")
    @GetMapping("{id}")
    public R<ArticleRes> articleDetails(@PathVariable String id) {
        return R.data(articleService.articleDetails(id));
    }
}
