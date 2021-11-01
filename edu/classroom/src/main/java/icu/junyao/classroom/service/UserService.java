package icu.junyao.classroom.service;

import icu.junyao.classroom.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.req.UserLoginReq;

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
     * @param userLoginReq {@link UserLoginReq}
     * @return 登录后的token
     */
    String login(UserLoginReq userLoginReq);
}
