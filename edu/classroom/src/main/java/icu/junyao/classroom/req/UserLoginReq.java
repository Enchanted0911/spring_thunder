package icu.junyao.classroom.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author johnson
 * @date 2021-11-01
 */
@Data
public class UserLoginReq {

    @NotEmpty(message = "手机号不能为空!")
    @Pattern(regexp = "^1[0-9]*$", message = "手机号格式错误!")
    private String mobile;

    @NotEmpty(message = "密码不能为空!")
    private String password;
}
