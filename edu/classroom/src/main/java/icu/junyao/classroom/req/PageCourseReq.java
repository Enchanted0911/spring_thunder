package icu.junyao.classroom.req;

import icu.junyao.common.entity.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-11-04
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageCourseReq extends PageCondition {
    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String createTimeSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
