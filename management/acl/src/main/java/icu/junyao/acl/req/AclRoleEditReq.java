package icu.junyao.acl.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author johnson
 * @date 2021-10-03
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AclRoleEditReq extends AclRoleReq{

    @NotBlank(message = "id不能为空!")
    private String id;
}
