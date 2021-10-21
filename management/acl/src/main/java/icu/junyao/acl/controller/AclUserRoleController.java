package icu.junyao.acl.controller;


import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.req.AclUserRoleReq;
import icu.junyao.acl.req.PageRoleReq;
import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.acl.res.AllAclRoleInfoRes;
import icu.junyao.acl.service.AclUserRoleService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    private final AclUserRoleService aclUserRoleService;

    @ApiOperation("根据用户id获取角色信息")
    @GetMapping("{id}")
    public R<List<AllAclRoleInfoRes>> gainRoleInfoByUserId(@PathVariable String id) {
        return R.data(aclUserRoleService.gainRoleInfoByUserId(id));
    }

    @ApiOperation("给用户分配角色")
    @PostMapping
    public R<Void> assignRoleToUser(@RequestBody AclUserRoleReq aclUserRoleReq) {
        aclUserRoleService.assignRoleToUser(aclUserRoleReq);
        return R.success();
    }
}

