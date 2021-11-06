package icu.junyao.classroom.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-11-04
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageTeacherReq extends PageCondition {
    private String teacherName;
}
