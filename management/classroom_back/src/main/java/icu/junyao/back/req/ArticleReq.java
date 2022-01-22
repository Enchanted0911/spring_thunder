package icu.junyao.back.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnson
 * @date 2022-01-20
 */
@Data
public class ArticleReq {

    private String subjectId;

    private String subjectParentId;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章标题")
    private String title;
}
