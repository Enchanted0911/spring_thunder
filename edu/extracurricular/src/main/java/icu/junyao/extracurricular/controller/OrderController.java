package icu.junyao.extracurricular.controller;


import icu.junyao.common.entity.R;
import icu.junyao.extracurricular.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("查询课程是否购买")
    @GetMapping("bought/{userId}/{courseId}")
    public R<Boolean> ifBought(@PathVariable String userId, @PathVariable String courseId) {
        return R.data(orderService.ifBought(userId, courseId));
    }


}

