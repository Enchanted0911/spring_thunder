package icu.junyao.classroom.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.req.PasswordEditReq;
import icu.junyao.classroom.req.UserEditReq;
import icu.junyao.classroom.req.UserLoginReq;
import icu.junyao.classroom.req.UserRegisterReq;
import icu.junyao.classroom.res.UserRes;
import icu.junyao.classroom.service.UserService;
import icu.junyao.classroom.util.JwtUtil;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;

    @ApiOperation("登录")
    @PostMapping("login")
    public R<String> login(@RequestBody @Valid UserLoginReq userLoginReq) {
        return R.data(userService.login(userLoginReq));
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public R<Void> register(@RequestBody @Valid UserRegisterReq userRegisterReq) {
        userService.register(userRegisterReq);
        return R.success();
    }

    @ApiOperation("修改用户信息")
    @PutMapping
    public R<Void> updateUserInfo(@RequestBody @Valid UserEditReq userEditReq) {
        userService.updateUserInfo(userEditReq);
        return R.success();
    }

    @ApiOperation("根据token获取用户的信息")
    @GetMapping
    public R<UserRes> gainUserInfoByToken(HttpServletRequest request) {
        DecodedJWT decodedJwt = jwtUtil.validateToken(request);
        return R.data(userService.gainUserInfoByToken(decodedJwt));
    }

    @ApiOperation("根据id获取用户的信息")
    @GetMapping("{id}")
    public R<UserRes> gainUserInfoById(@PathVariable String id) {
        return R.data(userService.gainUserInfoById(id));
    }

    @ApiOperation("修改密码")
    @PutMapping("pwd")
    public R<Void> updatePassword(@RequestBody @Valid PasswordEditReq passwordEditReq) {
        userService.updatePassword(passwordEditReq);
        return R.success();
    }

    @ApiOperation("统计某一天的注册人数")
    @PutMapping("statistic/{date}")
    public R<Integer> statisticRegister(@PathVariable String date) {
        return R.data(userService.statisticRegister(date));
    }
}

