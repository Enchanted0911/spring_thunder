package icu.junyao.back.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnson
 * @date 2021-10-24
 */
@Data
public class CourseChapterReq {

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;
}
