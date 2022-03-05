package icu.junyao.classroom.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author johnson
 * @date 2022-01-19
 */
@Data
public class ArticleRes {

    @ApiModelProperty(value = "获赞数")
    private Long likesNum;

    @ApiModelProperty(value = "收藏数")
    private Long collectsNum;

    @ApiModelProperty(value = "评论数")
    private Long commentsNum;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    private String subjectId;

    private String teacherName;

    private String title;

    private String subjectParentId;

    private String subjectName;

    private String subjectParentName;
}
