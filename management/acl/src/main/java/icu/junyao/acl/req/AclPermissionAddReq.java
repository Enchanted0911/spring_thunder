package icu.junyao.acl.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclPermissionAddReq {
    @ApiModelProperty(value = "父级id")
    @NotEmpty(message = "未指定父级权限!")
    private String pid;

    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "权限名不能为空!")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    @NotNull(message = "权限类型不能为空!")
    private Integer type;

    @ApiModelProperty(value = "权限值")
    private String permissionValue;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    private Integer status;

}
