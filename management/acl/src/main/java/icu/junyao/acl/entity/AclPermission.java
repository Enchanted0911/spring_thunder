package icu.junyao.acl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_acl_permission")
@ApiModel(value="AclPermission对象", description="权限")
public class AclPermission extends BaseEntity {

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

}
