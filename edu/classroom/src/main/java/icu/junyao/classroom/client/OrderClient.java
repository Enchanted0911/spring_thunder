package icu.junyao.classroom.client;

import icu.junyao.common.entity.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author johnson
 * @date 2021-11-06
 */
@Component
@FeignClient("service-extracurricular")
public interface OrderClient {

    /**
     * 查询课程是否购买
     *
     * @param userId 用户id
     * @param courseId 课程id
     * @return true为已购买
     */
    @ApiOperation("查询课程是否购买")
    @GetMapping("/rabbit/extracurricular/order/bought/{userId}/{courseId}")
    R<Boolean> ifBought(@PathVariable String userId, @PathVariable String courseId);
}
