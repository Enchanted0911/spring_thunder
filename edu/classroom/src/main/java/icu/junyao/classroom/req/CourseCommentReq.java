package icu.junyao.classroom.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class CourseCommentReq {
    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论不能为空!")
    @Size(max = 100, message = "评论超出最大长度限制!")
    private String content;
}
