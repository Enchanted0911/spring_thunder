package icu.junyao.acl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_acl_user_role")
@ApiModel(value="AclUserRole对象", description="")
public class AclUserRole extends BaseEntity {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "用户id")
    private String userId;


}
