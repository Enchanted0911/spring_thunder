package icu.junyao.acl.controller;


import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.req.PageRoleReq;
import icu.junyao.acl.res.AclRoleDetailRes;
import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.acl.service.AclRoleService;
import icu.junyao.common.entity.PageCondition;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/acl-role")
@RequiredArgsConstructor
public class AclRoleController {

    private final AclRoleService aclRoleService;

    @ApiOperation("获取角色分页列表")
    @GetMapping("page")
    public R<PageResult<AclRole>> pageRole(@Valid PageRoleReq pageRoleReq) {
        return R.data(aclRoleService.pageRole(pageRoleReq));
    }

    @ApiOperation("获取角色")
    @GetMapping("{id}")
    public R<AclRoleDetailRes> roleDetails(@PathVariable String id) {
        return R.data(aclRoleService.roleDetails(id));
    }
}

