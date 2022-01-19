package icu.junyao.back.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
