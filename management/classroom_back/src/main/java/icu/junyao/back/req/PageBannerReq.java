package icu.junyao.back.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-10-24
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageBannerReq extends PageCondition {
    private String linkUrl;
    private String title;
    private String start;
    private String end;
}
