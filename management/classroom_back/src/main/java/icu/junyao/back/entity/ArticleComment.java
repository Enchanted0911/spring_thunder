package icu.junyao.back.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * article_comment表
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_article_comment")
@ApiModel(value="文章评论对象", description="article_comment表")
public class ArticleComment extends BaseEntity {

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "评论内容")
    private String content;

}
