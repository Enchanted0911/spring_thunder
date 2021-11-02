package icu.junyao.classroom.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.req.PasswordEditReq;
import icu.junyao.classroom.req.UserEditReq;
import icu.junyao.classroom.req.UserLoginReq;
import icu.junyao.classroom.req.UserRegisterReq;
import icu.junyao.classroom.res.UserRes;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     *
     * @param userLoginReq {@link UserLoginReq}
     * @return 登录后的token
     */
    String login(UserLoginReq userLoginReq);

    /**
     * 用户注册
     *
     * @param userRegisterReq {@link UserRegisterReq}
     */
    void register(UserRegisterReq userRegisterReq);

    /**
     * 修改用户信息
     *
     * @param userEditReq {@link UserEditReq}
     */
    void updateUserInfo(UserEditReq userEditReq);

    /**
     * 根据token获取用户信息
     *
     * @param decodedJwt {@link DecodedJWT}
     * @return {@link UserRes}
     */
    UserRes gainUserInfoByToken(DecodedJWT decodedJwt);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return {@link UserRes}
     */
    UserRes gainUserInfoById(String id);

    /**
     * 修改密码
     *
     * @param passwordEditReq {@link PasswordEditReq}
     */
    void updatePassword(PasswordEditReq passwordEditReq);

    /**
     * 统计某一天的注册人数
     *
     * @param date 要统计的日期
     * @return 当天注册的人数
     */
    Integer statisticRegister(String date);
}
