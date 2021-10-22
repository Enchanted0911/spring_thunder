package icu.junyao.acl.controller;


import icu.junyao.acl.req.AclRolePermissionAddReq;
import icu.junyao.acl.res.AclPermissionRes;
import icu.junyao.acl.service.AclRolePermissionService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色权限 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "角色权限管理")
@RestController
@RequestMapping("/acl-role-permission")
@RequiredArgsConstructor
public class AclRolePermissionController {

    private final AclRolePermissionService aclRolePermissionService;

    @ApiOperation(value = "给角色分配权限")
    @PostMapping
    public R<Void> saveRolePermissionRelationShip(@RequestBody @Valid AclRolePermissionAddReq aclRolePermissionAddReq) {
        aclRolePermissionService.saveRolePermissionRelationShip(aclRolePermissionAddReq);
        return R.success();
    }

    @ApiOperation(value = "获取角色的权限")
    @GetMapping("{roleId}")
    public R<List<AclPermissionRes>> selectRolePermissionRelationShip(@PathVariable String roleId) {
        List<AclPermissionRes> aclPermissionList = aclRolePermissionService.selectRolePermissionRelationShip(roleId);
        return R.data(aclPermissionList);
    }
}

