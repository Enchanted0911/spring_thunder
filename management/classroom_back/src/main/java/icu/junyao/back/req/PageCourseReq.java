package icu.junyao.back.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-11-02
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageCourseReq extends PageCondition {
    private String title;
    private String status;
}
