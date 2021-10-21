package icu.junyao.acl.req;

import lombok.Data;

import java.util.List;

/**
 * @author johnson
 * @date 2021-10-13
 */
@Data
public class AclUserRoleReq {
    private String userId;
    private List<String> roleIdList;
}
