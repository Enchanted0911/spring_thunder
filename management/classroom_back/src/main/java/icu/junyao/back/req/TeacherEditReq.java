package icu.junyao.back.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author johnson
 * @date 2021-11-04
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TeacherEditReq extends TeacherReq{
    @NotBlank(message = "教师id不能为空!")
    private String id;
}
