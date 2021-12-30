package icu.junyao.back.controller;

import icu.junyao.back.constant.OssConstants;
import icu.junyao.back.service.OssService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("上传教师头像")
    @PostMapping("/teacher-avatar")
    public R<String> uploadOssFileTeachAvatar(@RequestPart("file") MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        return R.data(ossService.uploadPicture(file, OssConstants.TEACHER_AVATAR_PATH));
    }

    @ApiOperation("上传课程封面")
    @PostMapping("/course-cover")
    public R<String> uploadOssFileCourseCover(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.COURSE_COVER_PATH));
    }

    @ApiOperation("上传banner")
    @PostMapping("/banner")
    public R<String> uploadOssFileBanner(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.BANNER_PATH));
    }

    @ApiOperation("上传用户头像")
    @PostMapping("/member")
    public R<String> uploadOssFileMember(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.MEMBER_PATH));
    }

    @ApiOperation("上传管理用户头像")
    @PostMapping("/acl-user-avatar")
    public R<String> uploadOssFileAclUserAvatar(@RequestPart("file") MultipartFile file) {
        return R.data(ossService.uploadPicture(file, OssConstants.ACL_USER_AVATAR));
    }
}
