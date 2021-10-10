package icu.junyao.acl.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclUserReq {

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{2,20}$", message = "用户名格式错误!")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!密码长度必须在6-20位之间")
    private String password;

    private String nickName;

    private String avatar;
}
