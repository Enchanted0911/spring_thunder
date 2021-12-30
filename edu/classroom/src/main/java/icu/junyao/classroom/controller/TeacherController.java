package icu.junyao.classroom.controller;


import icu.junyao.classroom.req.PageTeacherReq;
import icu.junyao.classroom.res.TeacherRes;
import icu.junyao.classroom.service.TeacherService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取前四条热门教师")
    @GetMapping("hot-list")
    public R<List<TeacherRes>> list4HotTeacher() {
        return R.data(teacherService.list4HotTeacher());
    }

    @ApiOperation("分页获取教师")
    @GetMapping("page")
    public R<PageResult<TeacherRes>> pageTeacher(@Valid PageTeacherReq pageTeacherReq) {
        return R.data(teacherService.pageTeacher(pageTeacherReq));
    }

    @ApiOperation("获取教师详情")
    @GetMapping("{id}")
    public R<TeacherRes> teacherDetails(@PathVariable String id) {
        return R.data(teacherService.teacherDetails(id));
    }
}

