package icu.junyao.back.controller;


import icu.junyao.back.req.CourseBaseInfoEditReq;
import icu.junyao.back.req.CourseBaseInfoReq;
import icu.junyao.back.req.PageCourseReq;
import icu.junyao.back.res.CourseBaseInfoRes;
import icu.junyao.back.res.CourseChapterRes;
import icu.junyao.back.res.CoursePublishInfoRes;
import icu.junyao.back.res.CourseRes;
import icu.junyao.back.service.CourseService;
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

    @ApiOperation("获取课程大纲")
    @GetMapping("outline/{id}")
    public R<List<CourseChapterRes>> gainCourseOutline(@PathVariable String id) {
        return R.data(courseService.gainCourseOutline(id));
    }

    @ApiOperation("获取课程分页列表")
    @GetMapping("page")
    public R<PageResult<CourseRes>> pageCourse(@Valid PageCourseReq pageCourseReq) {
        return R.data(courseService.pageCourse(pageCourseReq));
    }

    @ApiOperation("新增课程-基本信息")
    @PostMapping
    public R<String> saveCourseBaseInfo(@RequestBody @Valid CourseBaseInfoReq courseBaseInfoReq) {
        return R.data(courseService.saveCourseBaseInfo(courseBaseInfoReq));
    }

    @ApiOperation("获取课程基本信息")
    @GetMapping("{id}")
    public R<CourseBaseInfoRes> gainCourseBaseInfo(@PathVariable String id) {
        return R.data(courseService.gainCourseBaseInfo(id));
    }

    @ApiOperation("修改课程基本信息")
    @PutMapping
    public R<Void> updateCourseBaseInfo(@RequestBody CourseBaseInfoEditReq courseBaseInfoEditReq) {
        courseService.updateCourseBaseInfo(courseBaseInfoEditReq);
        return R.success();
    }

    @ApiOperation("获取课程发布信息")
    @GetMapping("publish-info/{id}")
    public R<CoursePublishInfoRes> gainCoursePublishInfo(@PathVariable String id) {
        return R.data(courseService.gainCoursePublishInfo(id));
    }

    @ApiOperation("发布课程")
    @PutMapping("publish-info/{id}")
    public R<Void> publishCourse(@PathVariable String id) {
        courseService.publishCourse(id);
        return R.success();
    }

    @ApiOperation("删除课程")
    @DeleteMapping("{id}")
    public R<Void> removeCourse(@PathVariable String id) {
        courseService.removeCourse(id);
        return R.success();
    }
}

