package icu.junyao.acl.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author johnson
 * @date 2021-10-13
 */
@Data
public class AclUserEditReq {
    @NotBlank(message = "id不能为空!")
    private String id;

    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{2,20}$", message = "用户名格式错误!")
    private String username;

    private String nickName;

    private String avatar;
}
