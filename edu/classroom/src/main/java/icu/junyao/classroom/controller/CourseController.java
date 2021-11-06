package icu.junyao.classroom.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.req.PageCourseReq;
import icu.junyao.classroom.res.CourseChapterRes;
import icu.junyao.classroom.res.CourseDetailRes;
import icu.junyao.classroom.res.CourseRes;
import icu.junyao.classroom.service.CourseService;
import icu.junyao.classroom.util.JwtUtil;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final JwtUtil jwtUtil;

    @ApiOperation("获取前八条热门课程")
    @GetMapping("hot-list")
    public R<List<CourseRes>> list8HotCourse() {
        return R.data(courseService.list8HotCourse());
    }

    @ApiOperation("根据教师id获取课程")
    @GetMapping("teacher/{teacherId}")
    public R<List<CourseRes>> listCourseByTeacherId(@PathVariable String teacherId) {
        return R.data(courseService.listCourseByTeacherId(teacherId));
    }

    @ApiOperation("分页获取课程")
    @GetMapping("page")
    public R<PageResult<CourseRes>> pageCourse(@Valid PageCourseReq pageCourseReq) {
        return R.data(courseService.pageCourse(pageCourseReq));
    }

    @ApiOperation("获取课程大纲")
    @GetMapping("outline/{id}")
    public R<List<CourseChapterRes>> gainCourseOutline(@PathVariable String id) {
        return R.data(courseService.gainCourseOutline(id));
    }

    @ApiOperation("获取课程基本信息")
    @GetMapping("{id}")
    public R<CourseDetailRes> courseDetails(@PathVariable String id) {
        return R.data(courseService.courseDetails(id));
    }

    @ApiOperation("获取课程是否已购")
    @GetMapping("{id}")
    public R<Boolean> gainIfBuy(@PathVariable String id, HttpServletRequest request) {
        DecodedJWT decodedJwt = jwtUtil.validateToken(request);
        return R.data(courseService.gainIfBuy(id, decodedJwt));
    }
}

