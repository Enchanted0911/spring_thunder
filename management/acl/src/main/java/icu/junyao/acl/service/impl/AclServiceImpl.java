package icu.junyao.acl.service.impl;

import icu.junyao.acl.constant.CommonConstant;
import icu.junyao.acl.res.AclUserInfoRes;
import icu.junyao.acl.service.AclPermissionService;
import icu.junyao.acl.service.AclRoleService;
import icu.junyao.acl.service.AclService;
import icu.junyao.acl.service.AclUserRoleService;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclServiceImpl implements AclService {

    private final AclUserRoleService aclUserRoleService;
    private final AclPermissionService aclPermissionService;
    private final CacheManager cacheManager;
    @Override
    public AclUserInfoRes gainUserInfo() {
        // 先从缓存取数据
        JwtUser jwtUser = SecurityUtils.getUser();
        Cache cache = cacheManager.getCache(CacheConstants.PERMISSION_VALUES);
        if (cache != null && cache.get(jwtUser.getUsername()) != null) {
            return cache.get(jwtUser.getUsername(), AclUserInfoRes.class);
        }

        AclUserInfoRes aclUserInfoRes = new AclUserInfoRes();

        //根据当前用户id获取角色
        List<String> roleNameList = aclUserRoleService.gainCurrentUserRoleNameList();
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = aclPermissionService.gainPermissionValueListByAclUser(SecurityUtils.getUserId());

        BeanUtils.copyProperties(jwtUser, aclUserInfoRes);
        aclUserInfoRes.setRoles(roleNameList);
        aclUserInfoRes.setPermissionValueList(permissionValueList);

        if (cache != null) {
            cache.put(jwtUser.getUsername(), aclUserInfoRes);
        }
        return aclUserInfoRes;
    }
}
