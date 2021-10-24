package icu.junyao.back.controller;


import icu.junyao.back.req.CourseChapterEditReq;
import icu.junyao.back.req.CourseChapterReq;
import icu.junyao.back.res.CourseChapterDetailRes;
import icu.junyao.back.res.CourseChapterRes;
import icu.junyao.back.service.CourseChapterService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "课程章节管理")
@RestController
@RequestMapping("/course-chapter")
@RequiredArgsConstructor
public class CourseChapterController {

    private final CourseChapterService courseChapterService;

    @ApiOperation("新增章节")
    @PostMapping
    public R<Void> saveChapter(@RequestBody CourseChapterReq courseChapterReq) {
        courseChapterService.saveChapter(courseChapterReq);
        return R.success();
    }

    @ApiOperation("修改章节")
    @PutMapping
    public R<Void> updateChapter(@RequestBody CourseChapterEditReq courseChapterEditReq) {
        courseChapterService.updateChapter(courseChapterEditReq);
        return R.success();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{id}")
    public R<Void> deleteChapter(@PathVariable String id) {
        courseChapterService.deleteChapter(id);
        return R.success();
    }

    @ApiOperation("查询章节")
    @GetMapping("{id}")
    public R<CourseChapterDetailRes> chapterDetails(@PathVariable String id) {
        return R.data(courseChapterService.chapterDetails(id));
    }
}

