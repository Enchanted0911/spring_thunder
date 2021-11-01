package icu.junyao.classroom.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.entity.User;
import icu.junyao.classroom.mapper.UserMapper;
import icu.junyao.classroom.req.UserLoginReq;
import icu.junyao.classroom.service.UserService;
import icu.junyao.classroom.util.JwtUtil;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
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
}
