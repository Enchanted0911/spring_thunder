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
public class CourseSubsectionEditReq extends CourseSubsectionReq {
    @NotBlank(message = "未指定小节id!")
    private String id;
}
