package icu.junyao.classroom.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class UserRegisterReq {
    @NotBlank(message = "昵称不能为空!")
    @Size(min = 1, max = 25, message = "昵称最大长度不能超过25")
    @Pattern(regexp = "^\\S*$", message = "昵称不能包含空格!")
    private String nickname;

    @NotBlank(message = "手机号不能为空!")
    @Pattern(regexp = "^1[0-9]*$", message = "手机号格式错误!")
    private String mobile;

    @NotBlank(message = "密码不能为空!")
    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!")
    private String password;
}
