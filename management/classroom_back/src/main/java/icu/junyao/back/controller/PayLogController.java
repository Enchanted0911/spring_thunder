package icu.junyao.back.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Api(tags = "支付日志管理")
@RestController
@RequestMapping("/pay-log")
@RequiredArgsConstructor
public class PayLogController {

}

