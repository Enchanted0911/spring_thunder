package icu.junyao.acl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_acl_role_permission")
@ApiModel(value="AclRolePermission对象", description="角色权限")
public class AclRolePermission extends BaseEntity {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    private String permissionId;


}
