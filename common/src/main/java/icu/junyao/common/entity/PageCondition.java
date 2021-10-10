package icu.junyao.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author johnson
 * @date 2021-10-02
 */
@ApiModel("查询分页基础条件")
@Data
public class PageCondition {

    @Min(value = 1, message = "页码必须大于一!")
    @ApiModelProperty(value = "当前页码",example = "1")
    protected Integer page = 1;

    @Min(value = 1, message = "页大小必须大于一!")
    @ApiModelProperty(value = "每页展示数量",example = "10")
    protected Integer pageSize = 10;
}
