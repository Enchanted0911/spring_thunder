package icu.junyao.security.Res;

import lombok.Data;

/**
 * 登录返回
 *
 * @author johnson
 * @date 2021-10-02
 */
@Data
public class LoginRes {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private Long expiresIn;

    private String username;

    private String nickName;

    private String avatar;
}
