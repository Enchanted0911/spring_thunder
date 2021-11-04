package icu.junyao.back.controller;


import icu.junyao.back.req.CourseSubsectionEditReq;
import icu.junyao.back.req.CourseSubsectionReq;
import icu.junyao.back.res.CourseSubsectionRes;
import icu.junyao.back.service.CourseSubsectionService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "课程小节管理")
@RestController
@RequestMapping("/course-subsection")
@RequiredArgsConstructor
public class CourseSubsectionController {

    private final CourseSubsectionService courseSubsectionService;

    @ApiOperation("获取课程小节")
    @GetMapping("{id}")
    public R<CourseSubsectionRes> gainCourseSubsection(@PathVariable String id) {
        return R.data(courseSubsectionService.gainCourseSubsection(id));
    }

    @ApiOperation("新增课程小节")
    @PostMapping
    public R<Void> saveCourseSubsection(@RequestBody CourseSubsectionReq courseSubsectionReq) {
        courseSubsectionService.saveCourseSubsection(courseSubsectionReq);
        return R.success();
    }

    @ApiOperation("修改课程小节")
    @PutMapping
    public R<Void> updateCourseSubsection(@RequestBody CourseSubsectionEditReq courseSubsectionEditReq) {
        courseSubsectionService.updateCourseSubsection(courseSubsectionEditReq);
        return R.success();
    }

    @ApiOperation("删除课程小节")
    @DeleteMapping("{id}")
    public R<Void> removeCourseSubsection(@PathVariable String id) {
        courseSubsectionService.removeCourseSubsection(id);
        return R.success();
    }
}

