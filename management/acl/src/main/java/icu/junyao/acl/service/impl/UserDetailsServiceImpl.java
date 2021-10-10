package icu.junyao.acl.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.acl.constant.CommonConstant;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.service.AclPermissionService;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author johnson
 * @date 2021-10-02
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AclUserService aclUserService;
    private final AclPermissionService aclPermissionService;
    private final CacheManager cacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先从缓存中取用户
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return cache.get(username, JwtUser.class);
        }

        // 缓存没有, 从数据库中查出用户
        LambdaQueryWrapper<AclUser> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(AclUser::getUsername, username);
        AclUser aclUser = aclUserService.getOne(userLambdaQueryWrapper);
        if (aclUser == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        // 创建返回UserDetails实现类
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(aclUser, jwtUser);

        // 获取用户权限值集合
        List<String> authorities = aclPermissionService.gainPermissionValueListByAclUser(aclUser.getId());
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(Convert.toStrArray(authorities));

        // 设置用户权限
        jwtUser.setAuthorities(authorityList);
        if (cache != null) {
            cache.put(username, jwtUser);
        }
        return jwtUser;
    }

}
