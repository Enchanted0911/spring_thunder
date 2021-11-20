package icu.junyao.classroom.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class UserEditReq {
    @NotBlank
    private String id;

    @ApiModelProperty(value = "昵称")
    @NotBlank
    @Size(max = 25, message = "昵称太长了!")
    @Pattern(regexp = "^\\S*$", message = "昵称不能包含空格!")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    @Min(value = 1, message = "性别不能是其他的!")
    @Max(value = 2, message = "性别不能是其他的")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    @Min(value = 0, message = "年龄不能小于零!")
    @Max(value = 150, message = "神仙不能在人间")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    @Size(max = 25, message = "签名太长了!")
    private String sign;
}
