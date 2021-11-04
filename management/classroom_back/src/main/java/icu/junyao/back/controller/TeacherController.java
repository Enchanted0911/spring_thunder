package icu.junyao.back.controller;


import icu.junyao.back.req.PageTeacherReq;
import icu.junyao.back.req.TeacherEditReq;
import icu.junyao.back.req.TeacherReq;
import icu.junyao.back.res.CourseSubsectionRes;
import icu.junyao.back.res.TeacherRes;
import icu.junyao.back.service.TeacherService;
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
 * 讲师 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "教师管理")
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @ApiOperation("所有教师列表")
    @GetMapping
    public R<List<TeacherRes>> listTeacher() {
        return R.data(teacherService.listTeacher());
    }

    @ApiOperation("根据id删除教师")
    @DeleteMapping("{id}")
    public R<Void> removeTeacher(@PathVariable String id) {
        teacherService.removeTeacher(id);
        return R.success();
    }
    @ApiOperation("分页获取教师")
    @GetMapping("page")
    public R<PageResult<TeacherRes>> pageTeacher(@Valid PageTeacherReq pageTeacherReq) {
        return R.data(teacherService.pageTeacher(pageTeacherReq));
    }
    @ApiOperation("添加教师")
    @PostMapping
    public R<Void> saveTeacher(@RequestBody @Valid TeacherReq teacherReq) {
        teacherService.saveTeacher(teacherReq);
        return R.success();
    }
    @ApiOperation("修改教师")
    @PutMapping
    public R<Void> updateTeacher(@RequestBody @Valid TeacherEditReq teacherEditReq) {
        teacherService.updateTeacher(teacherEditReq);
        return R.success();
    }

    @ApiOperation("根据id获取教师")
    @GetMapping("{id}")
    public R<TeacherRes> teacherDetails(@PathVariable String id) {
        return R.data(teacherService.teacherDetails(id));
    }
}

