package icu.junyao.acl.controller;

import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.acl.service.AclService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "认证鉴权")
@RestController
@RequestMapping("/acl")
@RequiredArgsConstructor
public class AclController {

    private final AclService aclService;

    @ApiOperation("获取当前用户信息")
    @GetMapping("info")
    public R<AclUserInfoRes> info() {
        AclUserInfoRes userInfo = aclService.gainUserInfo();
        return R.data(userInfo);
    }
}
