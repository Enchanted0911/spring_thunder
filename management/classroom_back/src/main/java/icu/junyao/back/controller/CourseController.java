package icu.junyao.back.controller;


import icu.junyao.back.res.CourseChapterRes;
import icu.junyao.back.service.CourseService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

