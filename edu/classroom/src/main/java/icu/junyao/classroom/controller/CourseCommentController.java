package icu.junyao.classroom.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.req.CourseCommentReq;
import icu.junyao.classroom.req.PageCourseCommentReq;
import icu.junyao.classroom.res.CourseCommentRes;
import icu.junyao.classroom.service.CourseCommentService;
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
    private final JwtUtil jwtUtil;

    @ApiOperation("获取评论列表")
    @GetMapping("page")
    public R<PageResult<CourseCommentRes>> pageComment(@Valid PageCourseCommentReq pageCourseCommentReq) {
        return R.data(courseCommentService.pageComment(pageCourseCommentReq));
    }

    @ApiOperation("添加评论")
    @PostMapping
    public R<Void> saveComment(@RequestBody CourseCommentReq courseCommentReq, HttpServletRequest request) {
        DecodedJWT decodedJwt = jwtUtil.validateToken(request);
        courseCommentService.saveComment(courseCommentReq, decodedJwt);
        return R.success();
    }
}

