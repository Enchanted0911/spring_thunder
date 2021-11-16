package icu.junyao.back.controller;

import icu.junyao.back.constant.OssConstants;
import icu.junyao.back.service.OssService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author johnson
 * @since 2021-11-16
 */
@Api(tags = "OSS管理")
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
public class OssController {
    private final OssService ossService;

    @PostMapping
    @RequestMapping("/teachAvatar")
    public R<String> uploadOssFileTeachAvatar(@RequestPart("file") MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        return R.data(ossService.uploadPicture(file, OssConstants.TEACHER_AVATAR_PATH));
    }

    @PostMapping
    @RequestMapping("/courseCover")
    public R<String> uploadOssFileCourseCover(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.COURSE_COVER_PATH));
    }

    @PostMapping
    @RequestMapping("/banner")
    public R<String> uploadOssFileBanner(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.BANNER_PATH));
    }

    @PostMapping
    @RequestMapping("/member")
    public R<String> uploadOssFileMember(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.MEMBER_PATH));
    }
}
