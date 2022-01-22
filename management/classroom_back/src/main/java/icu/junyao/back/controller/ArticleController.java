package icu.junyao.back.controller;

import icu.junyao.back.req.ArticleEditReq;
import icu.junyao.back.req.ArticleReq;
import icu.junyao.back.req.PageArticleReq;
import icu.junyao.back.res.ArticleRes;
import icu.junyao.back.service.ArticleService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation("添加文章")
    @PostMapping
    public R<Void> saveArticle(@RequestBody ArticleReq articleReq) {
        articleService.saveArticle(articleReq);
        return R.success();
    }

    @ApiOperation("修改文章")
    @PutMapping
    public R<Void> updateArticle(@RequestBody ArticleEditReq articleEditReq) {
        articleService.updateArticle(articleEditReq);
        return R.success();
    }

    @ApiOperation("删除文章")
    @DeleteMapping("{id}")
    public R<Void> removeArticle(@PathVariable String id) {
        articleService.removeArticle(id);
        return R.success();
    }

    @ApiOperation("批量删除文章")
    @DeleteMapping("batch")
    public R<Void> removeArticle(@RequestBody List<String> idList) {
        articleService.removeArticle(idList);
        return R.success();
    }
}
