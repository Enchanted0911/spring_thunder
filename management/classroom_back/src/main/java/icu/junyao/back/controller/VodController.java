package icu.junyao.back.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import icu.junyao.back.vod.VodService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "视频管理")
@RestController
@RequestMapping("/vod")
@RequiredArgsConstructor
public class VodController {

    private final VodService vodService;

    @ApiOperation("删除视频")
    @DeleteMapping("{id}")
    public R<Void> removeAlyVideo(@PathVariable String id) {
        vodService.removeAlyVideo(id);
        return R.success();
    }

    @ApiOperation("上传视频")
    @PostMapping
    public R<String> uploadAlyVideo(MultipartFile file) {
        return R.data(vodService.uploadAlyVideo(file));
    }

    @ApiOperation("获取视频播放凭证")
    @GetMapping("videoSourceId")
    public R<String> gainPlayAuth(@PathVariable String videoSourceId) {
        return R.data(vodService.gainPlayAuth(videoSourceId));
    }
}
