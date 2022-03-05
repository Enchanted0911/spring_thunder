package icu.junyao.back.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2022-01-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageArticleReq extends PageCondition {

    private String title;

    private String subjectId;

    private String subjectParentId;

    private String createdBy;

}
