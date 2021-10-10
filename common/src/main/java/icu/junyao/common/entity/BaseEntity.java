package icu.junyao.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author johnson
 * @date 2021-10-02
 */
@Data
public abstract class BaseEntity {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @ApiModelProperty(value = "修改者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;
}
