package icu.junyao.acl.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclRoleReq {

    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空!")
    private String name;

    @ApiModelProperty(value = "角色编码")
    @NotEmpty(message = "角色编码不能为空!")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;
}
