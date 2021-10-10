package icu.junyao.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.entity.AclUserRole;
import icu.junyao.acl.mapper.AclUserRoleMapper;
import icu.junyao.acl.service.AclRoleService;
import icu.junyao.acl.service.AclUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, AclUserRole> implements AclUserRoleService {

    private final AclRoleService aclRoleService;

    @Override
    public List<String> gainCurrentUserRoleNameList() {
        if (null == SecurityUtils.getUser()) {
            throw new BadCredentialsException("当前没有登录用户");
        }

        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, SecurityUtils.getUserId());
        List<String> roleIdList = super.list(aclUserRoleLambdaQueryWrapper)
                .stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
        return aclRoleService.gainRoleNameListByRoleIdList(roleIdList);
    }

    @Override
    public List<String> gainCurrentUserRoleIdList(String aclUserId) {
        // 获取用户的角色id集合
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, aclUserId);
        return super.list(aclUserRoleLambdaQueryWrapper)
                .stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
    }
}
