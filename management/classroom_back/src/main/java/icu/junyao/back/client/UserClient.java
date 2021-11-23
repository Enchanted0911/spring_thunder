package icu.junyao.back.client;

import icu.junyao.common.entity.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author johnson
 * @date 2021-11-23
 */
@Component
@FeignClient("service-classroom")
public interface UserClient {

    /**
     * 统计某一天用户注册人数
     *
     * @param date 要统计的日期
     * @return 当天用户注册人数
     */
    @ApiOperation("统计某一天的注册人数")
    @PutMapping("/rabbit/classroom/user/statistic/{date}")
    R<Integer> statisticRegister(@PathVariable String date);
}
