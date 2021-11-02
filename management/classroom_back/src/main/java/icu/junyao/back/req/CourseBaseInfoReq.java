package icu.junyao.back.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class CourseBaseInfoReq {

    @ApiModelProperty(value = "课程讲师ID")
    @NotBlank(message = "请选择教师")
    private String teacherId;

    @ApiModelProperty(value = "课程学科ID")
    @NotBlank(message = "请选择课程所属学科")
    private String subjectId;

    @ApiModelProperty(value = "课程学科父级ID")
    @NotBlank(message = "请选择课程所属学科")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    @NotBlank(message = "课程标题不能为空")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    @Min(value = 0, message = "课程价格最低为0")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    @Min(value = 0, message = "总课时最低为0")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
}
