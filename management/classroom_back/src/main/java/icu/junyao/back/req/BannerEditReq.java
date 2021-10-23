package icu.junyao.back.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author johnson
 * @date 2021-10-24
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class BannerEditReq extends BannerReq {

    @NotEmpty(message = "id不能为空!")
    private String id;
}
