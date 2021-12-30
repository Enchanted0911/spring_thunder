package icu.junyao.acl.controller;


import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.req.AclUserEditReq;
import icu.junyao.acl.req.AclUserReq;
import icu.junyao.acl.req.PageUserReq;
import icu.junyao.acl.req.PasswordReq;
import icu.junyao.acl.res.AclUserDetailRes;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation("获取单个用户信息")
    @GetMapping("{id}")
    public R<AclUserDetailRes> userDetails(@PathVariable String id) {
        return R.data(aclUserService.userDetails(id));
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping
    public R<AclUserDetailRes> currentUserInfo() {
        return R.data(aclUserService.currentUserInfo());
    }

    @ApiOperation("新增管理用户")
    @PostMapping
    public R<Void> saveAclUser(@RequestBody @Valid AclUserReq aclUserReq) {
        aclUserReq.setPassword(passwordEncoder.encode(aclUserReq.getPassword()));
        aclUserService.saveAclUser(aclUserReq);
        return R.success();
    }

    @ApiOperation("获取管理用户分页列表")
    @GetMapping("page")
    public R<PageResult<AclUser>> pageUser(@Valid PageUserReq pageUserReq) {
        return R.data(aclUserService.pageUser(pageUserReq));
    }

    @ApiOperation("修改用户")
    @PutMapping
    public R<Void> updateUser(@RequestBody @Valid AclUserEditReq aclUserEditReq) {
        aclUserService.updateUser(aclUserEditReq);
        return R.success();
    }

    @ApiOperation("修改个人信息")
    @PutMapping("self-info")
    public R<Void> updateUserSelf(@RequestBody @Valid AclUserEditReq aclUserEditReq) {
        aclUserService.updateUserSelf(aclUserEditReq);
        return R.success();
    }

    @ApiOperation("修改密码")
    @PutMapping("password")
    public R<Void> updatePassword(@RequestBody @Valid PasswordReq passwordReq) {
        aclUserService.updatePassword(passwordReq);
        return R.success();
    }

    @ApiOperation("删除单个管理用户")
    @DeleteMapping("{id}")
    public R<Void> removeUser(@PathVariable String id) {
        aclUserService.removeUser(id);
        return R.success();
    }

    @ApiOperation("删除多个管理用户")
    @DeleteMapping
    public R<Void> removeBatchUser(@RequestBody List<String> idList) {
        aclUserService.removeBatchUser(idList);
        return R.success();
    }
}

