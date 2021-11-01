package icu.junyao.classroom.controller;


import icu.junyao.classroom.req.UserLoginReq;
import icu.junyao.classroom.service.UserService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("登录")
    @PostMapping("login")
    public R<String> pageComment(@RequestBody @Valid UserLoginReq userLoginReq) {
        return R.data(userService.login(userLoginReq));
    }
}

