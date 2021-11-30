package icu.junyao.acl.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author johnson
 * @date 2021-11-30
 */
@Data
public class PasswordReq {
    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!密码长度必须在6-20位之间")
    private String oldPassword;

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!密码长度必须在6-20位之间")
    private String newPassword;
}
