package icu.junyao.back.controller;

import icu.junyao.back.req.ArticleContentEditReq;
import icu.junyao.back.res.ArticleContentRes;
import icu.junyao.back.res.ArticleRes;
import icu.junyao.back.service.ArticleContentService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * article_content表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Api(tags = "文章内容管理")
@RestController
@RequestMapping("/article-content")
@RequiredArgsConstructor
public class ArticleContentController {

    private final ArticleContentService articleContentService;

    @ApiOperation("获取文章内容详情")
    @GetMapping("{id}")
    public R<ArticleContentRes> articleContentDetails(@PathVariable String id) {
        return R.data(articleContentService.articleContentDetails(id));
    }


    @ApiOperation("修改文章内容")
    @PutMapping
    public R<Void> updateArticleContent(@RequestBody ArticleContentEditReq articleContentEditReq) {
        articleContentService.updateArticleContent(articleContentEditReq);
        return R.success();
    }
}
