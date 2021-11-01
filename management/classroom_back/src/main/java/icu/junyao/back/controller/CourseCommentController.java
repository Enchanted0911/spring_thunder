package icu.junyao.back.controller;


import icu.junyao.back.req.CourseChapterReq;
import icu.junyao.back.req.PageCourseCommentReq;
import icu.junyao.back.res.CourseCommentRes;
import icu.junyao.back.service.CourseCommentService;
import icu.junyao.common.entity.PageCondition;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "课程评论管理")
@RestController
@RequestMapping("/course-comment")
@RequiredArgsConstructor
public class CourseCommentController {
    private final CourseCommentService courseCommentService;

    @ApiOperation("获取评论列表")
    @GetMapping("page")
    public R<PageResult<CourseCommentRes>> pageComment(@Valid PageCourseCommentReq pageCourseCommentReq) {
        return R.data(courseCommentService.pageComment(pageCourseCommentReq));
    }
}

