package icu.junyao.classroom.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author johnson
 * @date 2022-03-05
 */
@Data
public class ArticleCommentReq {
    @ApiModelProperty(value = "课程id")
    private String articleId;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论不能为空!")
    @Size(max = 100, message = "评论超出最大长度限制!")
    private String content;
}
