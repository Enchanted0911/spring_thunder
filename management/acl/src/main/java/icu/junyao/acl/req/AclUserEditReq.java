package icu.junyao.acl.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author johnson
 * @date 2021-10-13
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AclUserEditReq extends AclUserReq {
    @NotEmpty(message = "id不能为空!")
    private String id;
}
