package icu.junyao.classroom.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class PasswordEditReq {

    private String id;

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!")
    private String oldPassword;

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!")
    private String newPassword;
}
