package icu.junyao.back.controller;


import icu.junyao.back.res.SubjectOneRes;
import icu.junyao.back.service.SubjectService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "学科管理")
@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @ApiOperation("通过上传excel添加学科")
    @PostMapping
    public R<Void> saveSubjectByExcel(@RequestPart("file") MultipartFile file) {
        subjectService.saveSubjectByExcel(file, subjectService);
        return R.success();
    }

    @ApiOperation("获取学科列表")
    @GetMapping
    public R<List<SubjectOneRes>> gainSubject() {
        return R.data(subjectService.gainSubject());
    }

}

