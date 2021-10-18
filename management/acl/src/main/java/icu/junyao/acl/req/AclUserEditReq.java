package icu.junyao.acl.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-10-13
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AclUserEditReq extends AclUserReq {
    private String id;
}