package icu.junyao.classroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * article_content表
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_article_content")
@ApiModel(value="article_content对象", description="article_content表")
public class ArticleContent extends BaseEntity {

    @ApiModelProperty(value = "文章内容")
    private String content;

}
