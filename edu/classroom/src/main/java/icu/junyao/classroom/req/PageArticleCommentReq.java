package icu.junyao.classroom.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2022-01-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageArticleCommentReq extends PageCondition {
    private String articleId;
}
