package icu.junyao.acl.req;

import icu.junyao.common.entity.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-10-03
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageRoleReq extends PageCondition {
    private String roleName;
}
