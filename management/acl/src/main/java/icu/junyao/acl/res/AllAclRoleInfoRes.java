package icu.junyao.acl.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnson
 * @date 2021-10-20
 */
@Data
public class AllAclRoleInfoRes {
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    private Boolean checked;
}
