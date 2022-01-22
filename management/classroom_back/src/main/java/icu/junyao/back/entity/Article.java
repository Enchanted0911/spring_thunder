package icu.junyao.back.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * article表
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_article")
@ApiModel(value="article对象", description="article表")
public class Article extends BaseEntity {

    @ApiModelProperty(value = "获赞数")
    private Long likesNum;

    @ApiModelProperty(value = "收藏数")
    private Long collectsNum;

    @ApiModelProperty(value = "评论数")
    private Long commentsNum;

    private String subjectId;

    private String subjectParentId;
}
