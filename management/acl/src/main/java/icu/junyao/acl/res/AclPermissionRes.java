package icu.junyao.acl.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclPermissionRes {
    private String id;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
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

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "下级")
    private List<AclPermissionRes> children;

    @ApiModelProperty(value = "是否选中")
    private boolean selected;
}
