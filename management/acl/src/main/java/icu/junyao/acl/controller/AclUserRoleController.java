package icu.junyao.acl.controller;


import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/acl-user-role")
@RequiredArgsConstructor
public class AclUserRoleController {
}

