package icu.junyao.acl.controller;


import com.alibaba.fastjson.JSONObject;
import icu.junyao.acl.entity.AclPermission;
import icu.junyao.acl.req.AclPermissionAddReq;
import icu.junyao.acl.req.AclPermissionUpdateReq;
import icu.junyao.acl.service.AclPermissionService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/acl-permission")
@RequiredArgsConstructor
public class AclPermissionController {
    private final AclPermissionService aclPermissionService;

    @ApiOperation("获取当前用户树形权限")
    @GetMapping
    public R<List<AclPermission>> gainPermissionInfo() {
        List<AclPermission> permissionList = aclPermissionService.gainTreePermissionList();
        return R.data(permissionList);
    }

    @ApiOperation("获取当前用户菜单列表")
    @GetMapping("menu")
    public R<List<JSONObject>> gainMenuInfo() {
        List<JSONObject> menuList = aclPermissionService.gainMenuList();
        return R.data(menuList);
    }

    @ApiOperation("新增菜单")
    @PostMapping
    public R<Void> saveMenu(@RequestBody @Valid AclPermissionAddReq aclPermissionAddReq) {
        aclPermissionService.saveMenu(aclPermissionAddReq);
        return R.success();
    }

    @ApiOperation("修改菜单")
    @PutMapping
    public R<Void> updateMenu(@RequestBody @Valid AclPermissionUpdateReq aclPermissionUpdateReq) {
        aclPermissionService.updateMenu(aclPermissionUpdateReq);
        return R.success();
    }

    @ApiOperation("递归删除菜单")
    @DeleteMapping("{id}")
    public R<Void> deleteMenu(@PathVariable String id) {
        aclPermissionService.deleteMenu(id);
        return R.success();
    }
}

