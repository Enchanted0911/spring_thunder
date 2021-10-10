package icu.junyao.acl.res;

import lombok.Data;

import java.util.List;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclUserInfoRes {
    private String username;
    private String avatar;
    private List<String> roles;
    private List<String> permissionValueList;
}
