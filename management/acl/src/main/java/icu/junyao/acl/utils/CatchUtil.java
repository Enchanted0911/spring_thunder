package icu.junyao.acl.utils;

import cn.hutool.core.convert.Convert;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.mapper.AclUserMapper;
import icu.junyao.acl.service.AclPermissionService;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author johnson
 * @since 2021-12-28
 */
@RequiredArgsConstructor
@Component
public class CatchUtil {

    private final AclUserMapper aclUserMapper;
    private final AclPermissionService aclPermissionService;
    private final CacheManager cacheManager;

    public void freshAclUserInfo(AclUser aclUser) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache == null || (cache.get(aclUser.getUsername()) == null && !aclUser.getId().equals(SecurityUtils.getUserId()))) {
            return;
        }

        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(aclUser, jwtUser);


        // 获取用户权限值集合
        List<String> authorities = aclPermissionService.gainPermissionValueListByAclUser(aclUser.getId());
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(Convert.toStrArray(authorities));

        // 设置用户权限
        jwtUser.setAuthorities(authorityList);
        jwtUser.setPassword(SecurityUtils.getUser().getPassword());

        cache.evict(SecurityUtils.getUserName());
        cache.put(aclUser.getUsername(), jwtUser);
    }
}
