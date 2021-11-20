package icu.junyao.classroom.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.constant.UserConstants;
import icu.junyao.classroom.entity.User;
import icu.junyao.classroom.mapper.UserMapper;
import icu.junyao.classroom.req.PasswordEditReq;
import icu.junyao.classroom.req.UserEditReq;
import icu.junyao.classroom.req.UserLoginReq;
import icu.junyao.classroom.req.UserRegisterReq;
import icu.junyao.classroom.res.UserRes;
import icu.junyao.classroom.service.UserService;
import icu.junyao.classroom.util.JwtUtil;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final JwtUtil jwtUtil;
    
    @Override
    public String login(UserLoginReq userLoginReq) {

        // 获取用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(User::getMobile, userLoginReq.getMobile());
        User user = super.getOne(userLambdaQueryWrapper);

        // 判断是否存在该用户
        if (user == null) {
            throw BusinessResponseEnum.ACCOUNT_NOT_EXIST.newException();
        }

        // 判断密码是否正确
        String inputPassword = DigestUtil.md5Hex(userLoginReq.getPassword());
        if (!inputPassword.equals(user.getPassword())) {
            throw BusinessResponseEnum.PWD_ERROR.newException();
        }

        // 判断用户是否被禁用
        if (user.getIsDisabled()) {
            throw BusinessResponseEnum.ACCOUNT_DISABLED.newException();
        }

        return jwtUtil.createAccessToken(user);
    }

    @Override
    public void register(UserRegisterReq userRegisterReq) {
        // 手机号去重
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(User::getMobile, userRegisterReq.getMobile());
        User user = super.getOne(userLambdaQueryWrapper);
        if (user != null) {
            throw BusinessResponseEnum.MOBILE_EXIST.newException();
        }

        // 设置加密后的密码和默认头像
        user = new User();
        BeanUtils.copyProperties(userRegisterReq, user);
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        user.setAvatar(UserConstants.DEFAULT_AVATAR);
        super.save(user);
    }

    @Override
    public void updateUserInfo(UserEditReq userEditReq) {
        User user = new User();
        BeanUtils.copyProperties(userEditReq, user);
        super.updateById(user);
    }

    @Override
    public UserRes gainUserInfoByToken(DecodedJWT decodedJwt) {
        // 校验解析jwt
        if (decodedJwt == null) {
            throw BusinessResponseEnum.TOKEN_ERROR.newException();
        }

        String id = decodedJwt.getClaim("id").asString();

        return this.gainUserInfoById(id);
    }

    @Override
    public UserRes gainUserInfoById(String id) {
        User user = super.getById(id);
        if (user == null) {
            throw BusinessResponseEnum.USER_NOT_FOUND.newException();
        }

        UserRes userRes = new UserRes();
        BeanUtils.copyProperties(user, userRes);
        return userRes;
    }

    @Override
    public void updatePassword(PasswordEditReq passwordEditReq) {
        User user = super.getById(passwordEditReq.getId());

        // 校验原始密码
        if (!DigestUtil.md5Hex(passwordEditReq.getOldPassword()).equals(user.getPassword())) {
            throw BusinessResponseEnum.PWD_ERROR.newException();
        }

        // 判断用户是否被禁用
        if (user.getIsDisabled()) {
            throw BusinessResponseEnum.ACCOUNT_DISABLED.newException();
        }

        user.setPassword(DigestUtil.md5Hex(passwordEditReq.getNewPassword()));

        super.updateById(user);
    }

    @Override
    public Integer statisticRegister(String date) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.apply("DATE(created_time)={0}", date);
        return super.count(userLambdaQueryWrapper);
    }
}
