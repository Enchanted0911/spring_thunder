package icu.junyao.acl.controller;


import icu.junyao.acl.req.AclUserReq;
import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/acl-user")
@RequiredArgsConstructor
public class AclUserController {

    private final AclUserService aclUserService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation("新增管理用户")
    @PostMapping
    public R<AclUserInfoRes> info(@RequestBody @Valid AclUserReq aclUserReq) {
        aclUserReq.setPassword(passwordEncoder.encode(aclUserReq.getPassword()));
        aclUserService.saveAclUser(aclUserReq);
        return R.success();
    }
}

