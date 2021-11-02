package icu.junyao.classroom.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnson
 * @date 2021-10-24
 */
@Data
public class CourseCommentRes {
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    private String content;
}
