package icu.junyao.acl.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author johnson
 * @date 2021-10-03
 */
@Data
public class AclRolePermissionAddReq {
    @NotEmpty(message = "角色不能为空!")
    private String roleId;

    @NotEmpty(message = "权限集合不能为空!")
    private List<String> permissionIdList;
}
