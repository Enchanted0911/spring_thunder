package icu.junyao.classroom.controller;

import icu.junyao.classroom.service.AclUserTeacherService;
import icu.junyao.common.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * acl_user_teacher表 前端控制器
 * </p>
 *
 * @author johnson
 * @since 2022-02-27
 */
@Api(tags = "管理员账户关联教室账户管理")
@RestController
@RequestMapping("/acl-user-teacher")
@RequiredArgsConstructor
public class AclUserTeacherController {

    private final AclUserTeacherService aclUserTeacherService;

    @ApiOperation("根据教师id获取管理员id")
    @GetMapping("teacher-2-manager/{id}")
    public R<String> gainAclUserIdByTeacherId(@PathVariable String id) {
        return R.data(aclUserTeacherService.gainAclUserIdByTeacherId(id));
    }

    @ApiOperation("根据管理员id获取教师id")
    @GetMapping("manager-2-teacher/{id}")
    public R<String> gainTeacherIdByAclUserId(@PathVariable String id) {
        return R.data(aclUserTeacherService.gainTeacherIdByAclUserId(id));
    }
}
