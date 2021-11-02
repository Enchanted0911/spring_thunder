package icu.junyao.classroom.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-11-01
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageCourseCommentReq extends PageCondition {
    private String courseId;
}
