package icu.junyao.acl.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-10-03
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AclRoleEditReq extends AclRoleReq{
    private String id;
}
