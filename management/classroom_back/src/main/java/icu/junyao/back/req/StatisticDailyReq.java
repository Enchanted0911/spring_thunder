package icu.junyao.back.req;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

/**
 * @author johnson
 * @date 2021-11-23
 */
@Data
public class StatisticDailyReq {

    @NotEmpty(message = "类型不能为空")
    private String type;

    @NotEmpty(message = "开始时间不能为空")
    private String begin;

    @NotEmpty(message = "结束时间不能为空")
    private String end;
}
