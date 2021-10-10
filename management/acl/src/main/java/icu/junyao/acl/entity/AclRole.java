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
@TableName("t_acl_role")
@ApiModel(value="AclRole对象", description="")
public class AclRole extends BaseEntity {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;


}
